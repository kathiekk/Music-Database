package com.example.demo.controller;

import com.example.demo.model.SongDTO;
import com.example.demo.service.interfaces.SongService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) { this.songService = songService; }

    @PostMapping
    public SongDTO createSong(@RequestBody @NotNull SongDTO song) {
        return songService.saveSong(song);
    }

    @PutMapping("/{id}")
    public SongDTO updateSong(@PathVariable @NotNull UUID id, @RequestBody @NotNull SongDTO newSong) {
        return songService.updateSong(id, newSong);
    }

    @GetMapping("/all")
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public SongDTO getSongById(@PathVariable @NotNull UUID id) {
        return songService.getSongById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable @NotNull UUID id) {
        songService.deleteSong(id);
    }
}
