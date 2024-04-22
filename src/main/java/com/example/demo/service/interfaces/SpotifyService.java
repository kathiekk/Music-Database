package com.example.demo.service.interfaces;

import com.example.demo.DTOs.SpotifyResponseDTO;
import reactor.core.publisher.Mono;

public interface SpotifyService {
    /**
     * Retrieves featured playlist data from Spotify.
     * The retrieved data includes a variety of information about the playlist,
     * such as playlist details, track information, pagination information and more,
     * all encapsulated into a SpotifyResponseDTO object.
     *
     * @return A Mono emitting the SpotifyResponseDTO object which contains the JSON
     * string response from Spotify wrapped around the "response" field.
     */
    Mono<SpotifyResponseDTO> fetchFeaturedPlaylistData();
}
