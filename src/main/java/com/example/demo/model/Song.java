package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID id;

    private String title;

    @ManyToOne
    private Album album;

    public Song() {}

    public Song(String title, Album album) {
        this.title = title;
        this.album = album;
    }

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

    public String getTitle() {
        return title;
    }

    public Album getAlbum() {
        return album;
    }
}
