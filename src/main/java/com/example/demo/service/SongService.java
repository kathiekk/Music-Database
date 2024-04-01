package com.example.demo.service;

import com.example.demo.model.Song;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    public Song updateSong(UUID id, Song newSong) {
        return songRepository.findById(id)
                .map(song -> {
                    if (newSong.getTitle() != null) {
                        song.setTitle(newSong.getTitle());
                    }
                    if (newSong.getAlbum() != null) {
                        song.setAlbum(newSong.getAlbum());
                    }
                    return songRepository.save(song);
                })
                .orElseGet(() -> {
                    newSong.setId(id);
                    return songRepository.save(newSong);
                });
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(UUID id) {
        return songRepository.findById(id).orElse(null);
    }

    public void deleteSong(UUID id) {
        songRepository.deleteById(id);
    }
}