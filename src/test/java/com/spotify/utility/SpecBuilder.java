package com.spotify.utility;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.spotify.authmanager.AccessTokenGenerator;

import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static com.spotify.utility.ReadProperties.*;  // without class name I can directly write the method name 

public class SpecBuilder {

	
	public static RequestSpecification reqSpec() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		
		return new RequestSpecBuilder()
		
		.setBaseUri(getPropertiesFile("baseURI"))  // called directly readPropertiesFile() thie method
		.setContentType(ContentType.JSON)
		.addHeader("Authorization", " Bearer "+AccessTokenGenerator.getToken()).setConfig(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
		.setBasePath("v1")
		.addFilter(new AllureRestAssured())  // new method call add filter and creating a new constructor 
		.log(LogDetail.ALL).build();

		
		
	}
		
	
	
	
	public static ResponseSpecification resSpec()
	{
		
		return new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
			
		.log(LogDetail.ALL).build();
		
		
		
	}
	
	
	
	
	
	
}
