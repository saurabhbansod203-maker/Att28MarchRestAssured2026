package com.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Items {
	@JsonProperty("limit")
	public int getLimit() {
		return this.limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	int limit;

	@JsonProperty("next")
	public Object getNext() {
		return this.next;
	}

	public void setNext(Object next) {
		this.next = next;
	}

	Object next;

	@JsonProperty("offset")
	public int getOffset() {
		return this.offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	int offset;

	@JsonProperty("previous")
	public Object getPrevious() {
		return this.previous;
	}

	public void setPrevious(Object previous) {
		this.previous = previous;
	}

	Object previous;

	@JsonProperty("href")
	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	String href;

	@JsonProperty("total")
	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	int total;

	@JsonProperty("items")
	public ArrayList<Object> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<Object> items) {
		this.items = items;
	}

	ArrayList<Object> items;
}
