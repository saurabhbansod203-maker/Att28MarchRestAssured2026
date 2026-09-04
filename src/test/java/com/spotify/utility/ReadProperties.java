package com.spotify.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	public static String getPropertiesFile(String propToBeRead) throws IOException {

		String filepath = "src/test/resources/config.properties";

		FileInputStream fis= new FileInputStream(filepath);
		
		Properties property = new Properties();
		
		property.load(fis);
		
		String data = property.getProperty(propToBeRead);
        
       return data;
       
       
	}
	

	

}
