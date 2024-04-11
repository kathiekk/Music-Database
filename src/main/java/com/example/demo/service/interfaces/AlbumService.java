package com.example.demo.service.interfaces;

import com.example.demo.model.AlbumDTO;

import java.util.List;
import java.util.UUID;

public interface AlbumService {
    AlbumDTO saveAlbum(AlbumDTO albumDTO);
    AlbumDTO updateAlbum(UUID id, AlbumDTO albumDTO);
    List<AlbumDTO> getAllAlbums();
    AlbumDTO getAlbumById(UUID id);
    void deleteAlbum(UUID id);
}