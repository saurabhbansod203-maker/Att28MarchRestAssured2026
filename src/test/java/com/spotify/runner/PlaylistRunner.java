package com.spotify.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		
		features = {"src/test/resources/features"},
		glue = {"com.spotify.stepdefinitions"},
	 	plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
		tags= "@CreatePlaylist or @GetPlaylist or @addItemtoPlaylist or @addImageToPlaylist or @RemoveItemtoPlaylist"
		

		
		
		
		)











public class PlaylistRunner  extends AbstractTestNGCucumberTests{

}



