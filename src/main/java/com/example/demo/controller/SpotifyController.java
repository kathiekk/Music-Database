package com.example.demo.controller;

import com.example.demo.service.interfaces.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class SpotifyController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/spotify/featured-playlists")
    public Mono<String> getFeaturedPlaylists() {
        return spotifyService.getFeaturedPlaylists();
    }

}
