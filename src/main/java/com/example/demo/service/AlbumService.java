package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.AlbumDTO;
import com.example.demo.model.Artist;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public AlbumDTO saveAlbum(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setTitle(albumDTO.getTitle());
        Optional<Artist> artist = artistRepository.findById(albumDTO.getArtistId());
        if(artist.isPresent()) {
            album.setArtist(artist.get());
            artist.get().addAlbum(album);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No artist with such ID found");

        albumRepository.save(album);
        albumDTO.setAlbumID(album.getId());
        return albumDTO;
    }

    public AlbumDTO updateAlbum(UUID id, AlbumDTO albumDTO) {
        return albumRepository.findById(id)
                .map(album -> {
                    album.setTitle(albumDTO.getTitle());

                    if (albumDTO.getArtistId() != album.getArtistID()) {
                        Optional<Artist> artist = artistRepository.findById(albumDTO.getArtistId());
                        if (artist.isPresent()) {
                            album.setArtist(artist.get());
                            artist.get().addAlbum(album);
                        }
                        else
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No artist with such ID found");

                    }
                    albumRepository.save(album);
                    return albumDTO;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
    }

    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream().map(Album::toDTO).toList();
    }

    public AlbumDTO getAlbumById(UUID id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent())
            return album.get().toDTO();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found");
    }

    public void deleteAlbum(UUID id) {
            albumRepository.deleteById(id);
    }
}