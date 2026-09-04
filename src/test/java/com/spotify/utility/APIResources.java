package com.spotify.utility;

public enum APIResources {

	
	  createPlaylist("me/playlists"),
	  getPlaylist("playlists/{PlaylistId}"),
	  addItemtoPlaylist("playlists/{PlaylistId}/items"),
	 addCoverImagetoPlaylist("playlists/{PlaylistId}/images"),
	 removePlaylistItem("playlists/{PlaylistId}/items");
	
	  String resources;
	  
	  APIResources (String resources)
	  {
		  
		  this.resources= resources;
		  
		  
	  }
	  
	  
	  public String getResources()
	  {
		  return resources;
		  
		  
	  }
	
	
	
	
}
