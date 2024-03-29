package com.example.demo.service;

import com.example.demo.model.Artist;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {
    private ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(UUID id) {
        return artistRepository.findById(id);
    }

    public void deleteArtist(UUID id) {
        artistRepository.deleteById(id);
    }

    public Artist updateArtist(UUID id, Artist newArtist) {
        return artistRepository.findById(id).map(artist -> {
            artist.setName(newArtist.getName());
            artist.setAlbums(newArtist.getAlbums());
            return artistRepository.save(artist);
        }).orElseGet(() -> {
            newArtist.setId(id);
            return artistRepository.save(newArtist);
        });
    }
}
