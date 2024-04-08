package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.AlbumDTO;
import com.example.demo.service.AlbumService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public Album createAlbum(@RequestBody @NotNull AlbumDTO album) { return albumService.saveAlbum(album); }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable @NotNull UUID id, @RequestBody @NotNull AlbumDTO newAlbum) {
        return albumService.updateAlbum(id, newAlbum);
    }

    @GetMapping("/all")
    public List<Album> getAllAlbums() { return albumService.getAllAlbums(); }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable @NotNull UUID id) {
        return albumService.getAlbumById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable @NotNull UUID id) {
        albumService.deleteAlbum(id);
    }
}
