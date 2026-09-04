package com.spotify.authmanager;

import static io.restassured.RestAssured.*;
import static com.spotify.utility.ReadProperties.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.spotify.utility.CryptoUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AccessTokenGenerator {

	static String accessToken;
	static Instant expireTime;
	static JsonPath jp;

	// Lazy Initialization

	public static String getToken() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		if (accessToken == null || Instant.now().isAfter(expireTime))

		{

			Response resp = renewToken();

			jp = resp.jsonPath();

			accessToken = jp.getString("access_token");

			int expireTimeInSecond = jp.getInt("expires_in");

			expireTime = Instant.now().plusSeconds(expireTimeInSecond - 300);

		} else {
			System.out.println("Token is Good To use.........Not_Expired");

		}

		return accessToken;

	}

	public static Response renewToken() throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {


		
		String encryptToken = getPropertiesFile("refresh_token");
		String encryptClientID = getPropertiesFile("client_id");  
		String encryptClientSecret = getPropertiesFile("client_secret");

		String refreshToken = CryptoUtils.decryptData(encryptToken);
		String clientID = CryptoUtils.decryptData(encryptClientID);
		String clientSecret = CryptoUtils.decryptData(encryptClientSecret);

		HashMap<String, String> map = new HashMap<>();
		map.put("grant_type", "refresh_token");
		map.put("refresh_token", refreshToken);
		map.put("client_id", clientID);
		map.put("client_secret", clientSecret);
		
		baseURI = "https://accounts.spotify.com";
	
		Response response = given().header("ContentType", "application/x-www-form-urlencoded").formParams(map).when()
				.post("/api/token").then().extract().response();

		return response;

	}

	public static void main(String args[]) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		renewToken();

	}

}
