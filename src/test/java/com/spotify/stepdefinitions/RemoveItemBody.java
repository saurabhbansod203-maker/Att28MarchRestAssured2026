package com.spotify.stepdefinitions;

import java.util.ArrayList;
import java.util.List;

import com.spotify.pojo.RemovePlaylistItem;
import com.spotify.pojo.SpotifyItem;

public class RemoveItemBody {

	public RemovePlaylistItem removePlaylistItems(String uri, String snapshotId) {

		 // 1. Create the inner item and set its URI
		SpotifyItem si = new SpotifyItem();
		si.setUri(uri);

		 // 2. Add it to a list (since 'items' is an array)
	
		List<SpotifyItem> itemList =  new ArrayList<>();
		
		itemList.add(si);
		
		 // 3. Create the main root object
		
		RemovePlaylistItem rpi = new RemovePlaylistItem();
		rpi.setSnapshot_id(snapshotId);
		rpi.setItems(itemList);
		
		return rpi;
		
	}



}
