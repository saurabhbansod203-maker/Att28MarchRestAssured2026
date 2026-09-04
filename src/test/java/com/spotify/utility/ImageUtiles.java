package com.spotify.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ImageUtiles {

	
	public static String convertImagetoBased64(String filePath){
		try {
		File file = new File (filePath);
		
		byte[] fileContent = Files.readAllBytes(file.toPath());
	
		return Base64.getEncoder().encodeToString(fileContent);
		
 } catch (IOException e)
	{
	 
	 throw new RuntimeException("Failed to read image file at " + filePath, e);
	 
	}
		
		
		
	}
	
	
	
	
	
	
	
}
