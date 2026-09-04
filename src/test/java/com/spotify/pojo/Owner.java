package com.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Owner {
	@JsonProperty("href")
	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	String href;

	@JsonProperty("id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String id;

	@JsonProperty("type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	String type;

	@JsonProperty("uri")
	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	String uri;

	@JsonProperty("display_name")
	public Object getDisplay_name() {
		return this.display_name;
	}

	public void setDisplay_name(Object display_name) {
		this.display_name = display_name;
	}

	Object display_name;

	@JsonProperty("external_urls")
	public ExternalUrls getExternal_urls() {
		return this.external_urls;
	}

	public void setExternal_urls(ExternalUrls external_urls) {
		this.external_urls = external_urls;
	}

	ExternalUrls external_urls;
}
