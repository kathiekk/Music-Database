package com.example.demo.service.interfaces;

import com.example.demo.model.SongDTO;

import java.util.List;
import java.util.UUID;

public interface SongService {
    SongDTO saveSong(SongDTO songDTO);
    SongDTO updateSong(UUID id, SongDTO songDTO);
    List<SongDTO> getAllSongs();
    SongDTO getSongById(UUID id);
    void deleteSong(UUID id);
}
