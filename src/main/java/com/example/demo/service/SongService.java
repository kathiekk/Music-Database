package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.Song;
import com.example.demo.model.SongDTO;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public SongDTO saveSong(SongDTO songDTO) {
        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        Optional<Album> album = albumRepository.findById(songDTO.getAlbumID());
        album.ifPresent(song::setAlbum);
        songRepository.save(song);
        songDTO.setSongID(song.getId());
        return songDTO;
    }

    public SongDTO updateSong(UUID id, SongDTO songDTO) {
        return songRepository.findById(id).map(song -> {
                    song.setTitle(songDTO.getTitle());
                    UUID albumID = songDTO.getAlbumID();
                    if (albumID != song.getAlbumID()) {
                        Optional<Album> album = albumRepository.findById(albumID);
                        if (album.isPresent())
                            song.setAlbum(album.get());
                        else
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with such ID not found");
                    }
                    songRepository.save(song);
                    return songDTO;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
    }

    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songs.stream().map(Song::toDTO).toList();
    }

    public SongDTO getSongById(UUID id) {
        Optional<Song> song = songRepository.findById(id);
        if(song.isPresent())
            return song.get().toDTO();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found");
    }

    public void deleteSong(UUID id) {
        songRepository.deleteById(id);
    }
}