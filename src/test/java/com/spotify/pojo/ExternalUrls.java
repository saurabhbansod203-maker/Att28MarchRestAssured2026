package com.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalUrls {
	@JsonProperty("spotify")
	
	public String getSpotify() {
		return this.spotify;
	}

	public void setSpotify(String spotify) {
		this.spotify = spotify;
	}

	String spotify;
}
