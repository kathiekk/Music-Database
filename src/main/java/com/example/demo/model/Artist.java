package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String name;

    @OneToMany
    private List<Album> albums;

    public Artist() {}

    public void setId(UUID id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum(Album album) { albums.add(album); }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public ArtistDTO toDTO() {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setName(name);
        artistDTO.setArtistID(id);
        return artistDTO;
    }
}
