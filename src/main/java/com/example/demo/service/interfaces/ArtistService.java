package com.example.demo.service.interfaces;

import com.example.demo.model.ArtistDTO;

import java.util.List;
import java.util.UUID;

public interface ArtistService {
    ArtistDTO saveArtist(ArtistDTO artistDTO);
    ArtistDTO updateArtist(UUID id, ArtistDTO artistDTO);
    List<ArtistDTO> getAllArtists();
    ArtistDTO getArtistById(UUID id);
    void deleteArtist(UUID id);
}
