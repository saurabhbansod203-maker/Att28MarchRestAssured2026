package com.spotify.pojo;

import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePlaylistBody {

	
        @JsonInclude(JsonInclude.Include.NON_NULL)
	    @JsonProperty("public")
	    private String name;
	    private String description;
        private boolean pb ;
        
        
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public boolean isPb() {
			return pb;
		}
		public void setPb(boolean pb) {
			this.pb = pb;
		}
	

	
}
