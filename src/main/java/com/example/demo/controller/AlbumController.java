package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.AlbumDTO;
import com.example.demo.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public Album createAlbum(@RequestBody AlbumDTO album) {
        return albumService.saveAlbum(album);
    }

    @PutMapping("/{id}")
    public Optional<Album> updateAlbum(@PathVariable UUID id, @RequestBody AlbumDTO newAlbum) {
        return albumService.updateAlbum(id, newAlbum);
    }

    @GetMapping("/all")
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable UUID id) {
        return albumService.getAlbumById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable UUID id) {
        albumService.deleteAlbum(id);
    }
}
