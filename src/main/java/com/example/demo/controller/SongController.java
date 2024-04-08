package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.model.SongDTO;
import com.example.demo.service.SongService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping
    public Song createSong(@RequestBody @NotNull SongDTO song) {
        return songService.saveSong(song);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable @NotNull UUID id, @RequestBody @NotNull SongDTO newSong) {
        return songService.updateSong(id, newSong);
    }

    @GetMapping("/all")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable @NotNull UUID id) {
        return songService.getSongById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable @NotNull UUID id) {
        songService.deleteSong(id);
    }
}
