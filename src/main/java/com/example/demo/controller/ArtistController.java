package com.example.demo.controller;

import com.example.demo.model.Artist;
import com.example.demo.model.ArtistDTO;
import com.example.demo.service.ArtistService;
import jakarta.validation.constraints.NotNull;
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
    public Artist createArtist(@RequestBody @NotNull ArtistDTO artist) {
        return artistService.saveArtist(artist);
    }

    @GetMapping("/all")
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable @NotNull UUID id) {
        return artistService.getArtistById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable @NotNull UUID id) {
        artistService.deleteArtist(id);
    }

    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable @NotNull UUID id, @RequestBody @NotNull ArtistDTO newArtist) {
        return artistService.updateArtist(id, newArtist);
    }
}
