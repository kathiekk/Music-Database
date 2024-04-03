package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.Song;
import com.example.demo.model.SongDTO;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    SongService(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    public Song saveSong(SongDTO songDTO) {
        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        Optional<Album> album = albumRepository.findById(songDTO.getAlbumID());
        album.ifPresent(song::setAlbum);
        return songRepository.save(song);
    }

    public Optional<Song> updateSong(UUID id, SongDTO songDTO) {
        return songRepository.findById(id)
                .map(song -> {
                    if (songDTO.getTitle() != null) {
                        song.setTitle(songDTO.getTitle());
                    }
                    UUID albumID = songDTO.getAlbumID();
                    if (albumID != null && albumID != song.getAlbumID()) {
                        Optional<Album> album = albumRepository.findById(albumID);
                        album.ifPresent(song::setAlbum);
                    }
                    return songRepository.save(song);
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