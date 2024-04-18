package com.example.demo.service.interfaces;

import com.example.demo.DTOs.SpotifyResponseDTO;
import reactor.core.publisher.Mono;

public interface SpotifyService {
    /**
     * Retrieves featured playlists from Spotify.
     *
     * @return A Mono emitting the featured playlists.
     */
    Mono<SpotifyResponseDTO> getFeaturedPlaylists();
}
