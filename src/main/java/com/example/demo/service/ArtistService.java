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

    public ArtistDTO saveArtist(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setName(artistDTO.getName());
        artistRepository.save(artist);
        artistDTO.setArtistID(artist.getId());
        return artistDTO;
    }

    public ArtistDTO updateArtist(UUID id, ArtistDTO artistDTO) {
        return artistRepository.findById(id).map(artist -> {
            artist.setName(artistDTO.getName());
            artistRepository.save(artist);
            return artistDTO;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
    }

    public List<ArtistDTO> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        return artists.stream().map(Artist::toDTO).toList();
    }

    public ArtistDTO getArtistById(UUID id) {
            Optional<Artist> artist = artistRepository.findById(id);
            if(artist.isPresent())
                return artist.get().toDTO();
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found");
    }

    public void deleteArtist(UUID id) {
         artistRepository.deleteById(id);
    }
}
