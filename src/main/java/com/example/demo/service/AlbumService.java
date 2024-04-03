package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.AlbumDTO;
import com.example.demo.model.Artist;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public Album saveAlbum(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setTitle(albumDTO.getTitle());
        Optional<Artist> artist = artistRepository.findById(albumDTO.getArtistId());
        if(artist.isPresent()) {
            album.setArtist(artist.get());
            artist.get().addAlbum(album);
        }
        return albumRepository.save(album);
    }

    public Optional<Album> updateAlbum(UUID id, AlbumDTO albumDTO) {
        return albumRepository.findById(id)
                .map(album -> {
                    if (albumDTO.getTitle() != null) {
                        album.setTitle(albumDTO.getTitle());
                    }
                    if (albumDTO.getArtistId() != null && albumDTO.getArtistId() != album.getArtistID()) {
                        Optional<Artist> artist = artistRepository.findById(albumDTO.getArtistId());
                        if (artist.isPresent()) {
                            album.setArtist(artist.get());
                            artist.get().addAlbum(album);
                        }
                    }
                    return albumRepository.save(album);
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