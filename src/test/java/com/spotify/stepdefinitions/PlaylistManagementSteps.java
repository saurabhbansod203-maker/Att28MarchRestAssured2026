package com.spotify.stepdefinitions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.groovy.util.SystemUtil;
import org.hamcrest.Matchers;
import org.testng.Assert;

import com.spotify.pojo.Root;
import com.spotify.utility.APIResources;
import com.spotify.utility.DataLoader;
import com.spotify.utility.ImageUtiles;
import com.spotify.utility.ScenarioContext;
import com.spotify.utility.SpecBuilder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Step Definition class managing all lifecycle actions for Spotify Playlist API
 * operations. Architecture uses state-sharing fields to pass HTTP
 * Request/Response context between steps.
 */

public class PlaylistManagementSteps {

	// Dependency

	private final PlaylistBody pb = new PlaylistBody();
	private final RemoveItemBody rib = new RemoveItemBody();
	ScenarioContext sc = new ScenarioContext();

	// State Sharing Fields (Clean and explicit)
	private RequestSpecification request;
	private Response response;
	private Root resp;
	public String expectedID; // to survive throught scenarios make it static
    public static String getsnapshotID;
    
	/**
	 * Initializes the baseline RestAssured request specification context.
	 * Pre-condition: Core request builder configs must be present in SpecBuilder
	 * utility.
	 */

	@Given("the user is authenticated with a valid Spotify access token")
	public void the_user_is_authenticated_with_a_valid_spotify_access_token() throws IOException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		// Initialize base specification
		// Build baseline state context and isolate logging to failures only to keep CI
		// console clean

		this.request = given(SpecBuilder.reqSpec()).log().ifValidationFails();

	}

	/**
	 * Attaches a serialized POJO body intended for playlist instantiation.
	 * 
	 * @param name        The descriptive target name of the target playlist
	 * @param description Brief contextual metadata summarizing the target playlist
	 */

	@Given("the request payload is prepared with name {string} and description {string}")
	public void the_request_payload_is_prepared_with_name_and_description(String Name, String Description) {

		// Safe builder pattern reassignment

		this.request = this.request.body(pb.createPlaylist(Name, Description));

	}

	/**
	 * Maps track tracking data and snapshot records to prepare a specific track
	 * teardown payload.
	 * 
	 * @param uri        Unique Spotify item track location signature
	 * @param snapshotId Dynamic identifier tracking specific playlist state
	 *                   iteration changes
	 */
	@Given("the request payload is prepared with uri {string} and description snapshot_id {string}")
	public void the_request_payload_is_prepared_with_uri_and_description_snapshot_id(String uri, String snapshot_id) {

		this.request = this.request.body(rib.removePlaylistItems(uri, getsnapshotID));
	}

	/**
	 * Dynamically resolves target endpoint paths and executes the designated raw
	 * HTTP action.
	 * 
	 * @param method   Expected target transaction Verb (GET, POST, PUT, DELETE)
	 * @param resource The API resource route identifier to look up via APIResources
	 *                 ENUM maps
	 */

	@When("the user sends a {string} request to the {string} endpoint")
	public void the_user_sends_a_request_to_the_endpoint(String method, String resource) {

		// Safe string comparison handling resource endpoint dynamically

		APIResources apiResources = APIResources.valueOf(resource);

		System.out.println(apiResources.getResources());

		if (method.equalsIgnoreCase("POST")) {

			this.response = this.request.when().post(apiResources.getResources());

		} else if (method.equalsIgnoreCase("GET"))

		{

			this.response = this.request.when().get(apiResources.getResources());

		}

		else if (method.equalsIgnoreCase("PUT"))

		{

			this.response = this.request.when().put(apiResources.getResources());

		} else if (method.equalsIgnoreCase("DELETE"))

		{

			this.response = this.request.when().delete(apiResources.getResources());

		}
	}

	/**
	 * Evaluates HTTP status code validity before proceeding to parse message body
	 * states.
	 * 
	 * @param expectedStatusCode Primary explicit numeric verification boundary
	 *                           assertion
	 */

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(Integer expectedstatusCode) {

		// Validate status baseline first before trying to process body contents
		// 1. Always assert the status code baseline first
		// Ensure HTTP assertions provide rich failure logging inside distributed test
		// run environments
		Assert.assertEquals(this.response.getStatusCode(), expectedstatusCode,
				"API Status Verification Assertion Failed!");

	}

	/**
	 * Validates structured fields against local JSON schemas and deserializes
	 * content to runtime POJO trees.
	 */
	@Then("the response body should match the playlist JSON schema")
	public void the_response_body_should_match_the_playlist_json_schema() {

		// Deserialize response payload directly into POJO structure

		// Structural field compliance tests + automated instantiation mapping logic

		this.resp = this.response.then().spec(SpecBuilder.resSpec())
				.body(matchesJsonSchemaInClasspath("spotify-playlist-schema.json")).body("owner.type", equalTo("user"))
				.body("id", Matchers.notNullValue()).body("id", matchesPattern("^[0-9a-zA-Z]+$")).extract().response()
				.as(Root.class);

	}

	/**
	 * Extracts structural resource identities and commits state mappings globally
	 * across the pipeline context cache.
	 * 
	 * @param id Field structural token mapping indicator
	 */

	@Then("the response body should contain a valid {string}")
	public void the_response_body_should_contain_a_valid(String id) {

		// actualResponse = resp.as(Root.class);

		String extractedID = this.resp.getId();

		System.out.println("This is extracted Id from Create Playlist Response: " + extractedID);

		// FIX: Save to scenario context cache so the next scenario can read it
		
		ScenarioContext.set("Playlist_ID", extractedID);
		
		String expectedID= (String) ScenarioContext.get("Playlist_ID");
		
		System.out.println(expectedID);

		// Persist dynamic variable configurations across thread context queues
		//Playlist_ID = extractedID;
		
		
	//	ScenarioContext.set("Playlist_ID", extractedID);
		System.out.println("Saved ID to Cache: " + extractedID);
		System.out.println("Saved ID to Cache: " + expectedID);
	}

	/**
	 * Verifies identity string parameters match fields mapped directly inside
	 * active deserialized models.
	 * 
	 * @param string               Field key context matching tag
	 * @param expectedPlaylistName Intended textual title data match verification
	 *                             boundary
	 */

	@Then("the playlist {string} in the response should match {string}")
	public void the_playlist_in_the_response_should_match(String string, String PlaylistName) {

		String playlistName = this.resp.getName();

		Assert.assertEquals(playlistName, PlaylistName);

	}

	/**
	 * Evaluates system classification metrics inside current active models.
	 * 
	 * @param key           Functional parameter structural check reference key
	 * @param expectedValue Expected target classification code signature type match
	 *                      target
	 */

	@Then("the playlist {string} should be {string}")
	public void the_playlist_should_be(String key, String value) {

		String playlistType = resp.getType();

		Assert.assertEquals(playlistType, value);

		System.out.println(playlistType);

	}

	/**
	 * Asserts targeted tracking key items against active localized system values.
	 * 
	 * @param id Verification target entity signature profile
	 */

	@Then("the playlist id in the response should match {string}")
	public void the_playlist_id_in_the_response_should_match(String id) {

		String getextractedID = this.resp.getId();

		System.out.println("This is extracted id from Get response : " + getextractedID);
		
		String expectedID= (String) ScenarioContext.get("Playlist_ID");

		Assert.assertEquals(expectedID, getextractedID);
	}

	/**
	 * Hooks up contextual state variable properties to runtime HTTP route parameter
	 * locations.
	 * 
	 * @param pathParamKey The key path variable literal string value to append
	 */

	@Given("the user is having valid playlist ID {string}")
	public void the_user_is_having_valid_playlist_id(String pathParamKey) {

		String playlistID = (String) ScenarioContext.get("Playlist_ID");

		this.request = this.request.pathParam(pathParamKey, playlistID);

	}

	/**
	 * Injects fully static custom raw string structures parsed straight from
	 * external files.
	 * 
	 * @param dataKey High-performance dictionary key pointer mapped within test
	 *                data configurations
	 */

	@Given("the request payload is loaded from data cluster key {string}")
	public void the_request_payload_is_loaded_from_data_cluster_key(String data_key) {

		// 1. Fetch the exact JSON block string using your data loader utility

		String jsonBody = DataLoader.getPlayload(data_key);

		// 2. Pass it directly into your active RestAssured specification
		this.request = this.request.body(jsonBody);

	}

	/**
	 * Parses out precise state verification tracking hashes using quick inline JSON
	 * Path evaluators.
	 * 
	 * @param expectedSnapshotId Target version string tracker to evaluate
	 */

	@Then("the response body should contains {string}")
	public void the_response_body_should_contains(String snapshotID) {

		this.response = this.response.then().spec(SpecBuilder.resSpec()).extract().response();

		JsonPath jp = response.jsonPath();

		 getsnapshotID = jp.getString("snapshot_id");
		 
		 snapshotID = getsnapshotID;

		System.out.println("The SnapShot ID is :"+ getsnapshotID);

	}

	/**
	 * Packages visual image data assets as raw Base64 payloads utilizing custom
	 * binary context serialization streams.
	 */

	@Given("the user request payload is prepared with encoded Base64Image text")
	public void the_user_request_payload_is_prepared_with_encoded_base64image_text()
			throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {

		// 1. Convert the file to a base64 string
		String base64Image = ImageUtiles.convertImagetoBased64("src/test/resources/testdata/jpegsystems-home.jpg");

		this.request = this.request.log().ifValidationFails();

		// 2. Set the Content-Type header to image/jpeg & // Clear previous logs and
		// only log if validation fails to keep console clean
		this.request = this.request.contentType("image/jpeg");

		// 3. Inject the EncoderConfig so RestAssured handles the text serialization
		// cleanly
		this.request = this.request.config(config()
				.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("image/jpeg", ContentType.TEXT)));

		// 4. Attach the base64 string payload to the request body
		this.request = this.request.body(base64Image);

	}

}
