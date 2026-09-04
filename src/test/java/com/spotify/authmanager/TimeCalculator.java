package com.spotify.authmanager;

import java.time.Instant;

import org.testng.annotations.Test;

public class TimeCalculator {
	
	static Instant expireTime;
	
	@Test
	public static void timeCalculator()
	{
		
		
		Instant currentTime = Instant.now();
		
		System.out.println(currentTime);
		
		Instant expireTime = Instant.now().plusSeconds(3600-300);
		
		System.out.println(expireTime);
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
