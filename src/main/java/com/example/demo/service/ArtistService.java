package com.example.demo.service;

import com.example.demo.model.Artist;
import com.example.demo.model.ArtistDTO;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist saveArtist(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setName(artistDTO.getName());
        return artistRepository.save(artist);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(UUID id) {
            Optional<Artist> artist = artistRepository.findById(id);
            if(artist.isPresent())
                return artist.get();
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found");
    }

    public void deleteArtist(UUID id) {
         artistRepository.deleteById(id);
    }

    public Artist updateArtist(UUID id, ArtistDTO artistDTO) {
        return artistRepository.findById(id).map(artist -> {
            artist.setName(artistDTO.getName());
            return artistRepository.save(artist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
    }
}
