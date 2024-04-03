package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.Optional;
import java.util.UUID;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String title;

    @ManyToOne
    private Album album;

    public Song() {}

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAlbumID() { return album.getId(); }

    public String getTitle() {
        return title;
    }

    public Album getAlbum() {
        return album;
    }
}
