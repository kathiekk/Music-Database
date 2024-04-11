package com.example.demo.controller;

import com.example.demo.model.AlbumDTO;
import com.example.demo.service.interfaces.AlbumService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) { this.albumService = albumService; }

    @PostMapping
    public AlbumDTO createAlbum(@RequestBody @NotNull AlbumDTO album) { return albumService.saveAlbum(album); }

    @PutMapping("/{id}")
    public AlbumDTO updateAlbum(@PathVariable @NotNull UUID id, @RequestBody @NotNull AlbumDTO newAlbum) {
        return albumService.updateAlbum(id, newAlbum);
    }

    @GetMapping("/all")
    public List<AlbumDTO> getAllAlbums() { return albumService.getAllAlbums(); }

    @GetMapping("/{id}")
    public AlbumDTO getAlbumById(@PathVariable @NotNull UUID id) {
        return albumService.getAlbumById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable @NotNull UUID id) {
        albumService.deleteAlbum(id);
    }
}
