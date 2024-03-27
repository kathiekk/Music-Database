package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Album album;

    public Song(String title, Album album) {
        this.title = title;
        this.album = album;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Album getAlbum() {
        return album;
    }
}
