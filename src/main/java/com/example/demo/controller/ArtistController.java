package com.example.demo.controller;

import com.example.demo.model.Artist;
import com.example.demo.model.ArtistDTO;
import com.example.demo.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public Artist createArtist(@RequestBody ArtistDTO artist) {
        return artistService.saveArtist(artist);
    }

    @GetMapping("/all")
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public Optional<Artist> getArtistById(@PathVariable UUID id) {
        return artistService.getArtistById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable UUID id) {
        artistService.deleteArtist(id);
    }

    @PutMapping("/{id}")
    public Optional<Artist> updateArtist(@PathVariable UUID id, @RequestBody ArtistDTO newArtist) {
        return artistService.updateArtist(id, newArtist);
    }
}
