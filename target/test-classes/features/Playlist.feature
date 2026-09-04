Feature: Spotify Playlist Management

As a Spotify API consumer 
I want to create a new playlist via API
So that users can oraganize and retrieve their favorite tracks

Background: 

Given the user is authenticated with a valid Spotify access token

@CreatePlaylist
Scenario Outline: Successfully creating a new Playlists 
Given the request payload is prepared with name "<PlaylistName>" and description "<Description>"
When the user sends a "POST" request to the "createPlaylist" endpoint
Then the response status code should be 201
    And the response body should match the playlist JSON schema
    And the response body should contain a valid "id"
    And the playlist "name" in the response should match "<PlaylistName>"
    And the playlist "type" should be "playlist"
    
    Examples:

      | PlaylistName       | Description                          |
      | Chill Vibes 2026 | Lofi beats for studying       |
      
 @GetPlaylist     
Scenario: Successfully fetching a created Playlist 
Given the user is having valid playlist ID "PlaylistId"
When the user sends a "GET" request to the "getPlaylist" endpoint
Then the response status code should be 200
   And the response body should match the playlist JSON schema
    And the response body should contain a valid "name"
    And the playlist id in the response should match "PlaylistId"
    
 @addItemtoPlaylist 
 Scenario Outline: Successfully adding item to playlist 
 
 Given the user is having valid playlist ID "PlaylistId"
 And the request payload is loaded from data cluster key "<data_key>"
 When the user sends a "POST" request to the "addItemtoPlaylist" endpoint
 Then the response status code should be 201 
 And the response body should contains "snapshot_id"
 
 Examples:

|data_key                               |
|valid_track_at_index_zero |
 
 @addImageToPlaylist
Scenario: Succesfully adding cover image to playlist
 
Given the user is having valid playlist ID "PlaylistId" 
And the user request payload is prepared with encoded Base64Image text
When the user sends a "PUT" request to the "addCoverImagetoPlaylist" endpoint
 Then the response status code should be 202
 
@RemoveItemtoPlaylist
 Scenario Outline:: Succesfully removing item from playlist
 Given the user is having valid playlist ID "PlaylistId" 
 And the request payload is prepared with uri "<uri>" and description snapshot_id "<snapshot_id>"
 When the user sends a "DELETE" request to the "removePlaylistItem" endpoint
Then the response status code should be 200
And the response body should contains "snapshot_id"

Examples:

|   	uri   																 |

|spotify:track:3gixnmepHSsyAuho34rprN  |    



 
 
 
 
 
      
      
      
      
      
      
      