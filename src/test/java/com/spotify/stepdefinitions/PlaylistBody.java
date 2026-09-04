package com.spotify.stepdefinitions;

import com.spotify.pojo.CreatePlaylistBody;

public class PlaylistBody {

	
	
	public CreatePlaylistBody createPlaylist(String name, String description)
	{
		
		CreatePlaylistBody cpb = new CreatePlaylistBody();
		
		cpb.setName(name);
		cpb.setDescription(description);
		
		return cpb;
		
	}
	
	
	
	
	
}
