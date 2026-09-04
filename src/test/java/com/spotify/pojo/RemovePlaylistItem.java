package com.spotify.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemovePlaylistItem {

	    
    @JsonProperty("items")
      private List<SpotifyItem> items;  // creating class of SpotifyItem

	    
		@JsonProperty("snapshot_id")
		private String snapshot_id;
		
	
	public List<SpotifyItem> getItems() {
		return items;
	}

	  public void setItems(List<SpotifyItem> items) {
		  this.items = items;
	  }

	  public String getSnapshot_id() {
		  return snapshot_id;
	  }

	  public void setSnapshot_id(String snapshot_id) {
		  this.snapshot_id = snapshot_id;
	  }


	
	
	
}
