package com.spotify.utility;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataLoader {

	
	private static final String dataFilePath = "src/test/resources/testdata/playlist_data.json";
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static String getPlayload(String datakey) 
	{
	    try {
	        // 1. Read the global data file
	    	JsonNode rootNode = mapper.readTree(new File(dataFilePath));
	    	
	    	 // 2. Fetch the specific test case data block
	    	JsonNode payloadNode = rootNode.get(datakey);
	    	
	    	if(payloadNode==null)
	    	{
	    		throw new RuntimeException("Test Data Key '" + datakey + "' not found in "+ dataFilePath);
	    	
	    	}
	    	
	    	// 3. Return the clean JSON block as a string
	    	
	    	return payloadNode.toString();
	    	
	    	

	    } catch (IOException e)
	    {
	    	
	    	 throw new RuntimeException("Failed to read external test data file: " + e.getMessage(), e);
	    }
	    
	   
	    	
	  
		
		
	}
	
	
	
	
	
	

}

	
	
	
