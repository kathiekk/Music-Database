package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Album updateAlbum(UUID id, Album newAlbum) {
        return albumRepository.findById(id)
                .map(album -> {
                    if (newAlbum.getTitle() != null) {
                        album.setTitle(newAlbum.getTitle());
                    }
                    if (newAlbum.getArtist() != null) {
                        album.setArtist(newAlbum.getArtist());
                    }
                    if (newAlbum.getSongs() != null) {
                        album.setSongs(newAlbum.getSongs());
                    }
                    return albumRepository.save(album);
                })
                .orElseGet(() -> {
                    newAlbum.setId(id);
                    return albumRepository.save(newAlbum);
                });
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbumById(UUID id) {
        return albumRepository.findById(id).orElse(null);
    }

    public void deleteAlbum(UUID id) {
        albumRepository.deleteById(id);
    }
}