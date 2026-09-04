package com.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Root {
	@JsonProperty("collaborative")
	public boolean getCollaborative() {
		return this.collaborative;
	}

	public void setCollaborative(boolean collaborative) {
		this.collaborative = collaborative;
	}

	boolean collaborative;

	@JsonProperty("description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	String description;

	@JsonProperty("external_urls")
	public ExternalUrls getExternal_urls() {
		return this.external_urls;
	}

	public void setExternal_urls(ExternalUrls external_urls) {
		this.external_urls = external_urls;
	}

	ExternalUrls external_urls;

	@JsonProperty("followers")
	public Followers getFollowers() {
		return this.followers;
	}

	public void setFollowers(Followers followers) {
		this.followers = followers;
	}

	Followers followers;

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

	@JsonProperty("images")
	public ArrayList<Object> getImages() {
		return this.images;
	}

	public void setImages(ArrayList<Object> images) {
		this.images = images;
	}

	ArrayList<Object> images;

	@JsonProperty("primary_color")
	public Object getPrimary_color() {
		return this.primary_color;
	}

	public void setPrimary_color(Object primary_color) {
		this.primary_color = primary_color;
	}

	Object primary_color;

	@JsonProperty("name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String name;

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

	@JsonProperty("owner")
	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	Owner owner;

	@JsonProperty("public")
	public boolean getMypublic() {
		return this.mypublic;
	}

	public void setMypublic(boolean mypublic) {
		this.mypublic = mypublic;
	}

	boolean mypublic;

	@JsonProperty("snapshot_id")
	public String getSnapshot_id() {
		return this.snapshot_id;
	}

	public void setSnapshot_id(String snapshot_id) {
		this.snapshot_id = snapshot_id;
	}

	String snapshot_id;

	@JsonProperty("items")
	public Items getItems() {
		return this.items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	Items items;
}
