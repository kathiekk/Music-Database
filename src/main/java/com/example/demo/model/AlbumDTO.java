package com.example.demo.model;

import java.util.UUID;

public class AlbumDTO {
    private String title;
    private UUID artistID;

    public void setTitle(String title) { this.title = title; }
    public void setArtistId(UUID artistId) { this.artistID = artistId; }
    public String getTitle() { return title; }
    public UUID getArtistId() { return artistID; }
}
