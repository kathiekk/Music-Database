package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AlbumDTO {
    private UUID albumID;
    @NotNull
    private String title;
    @NotNull
    private UUID artistID;

    public void setTitle(String title) { this.title = title; }
    public void setArtistId(UUID artistID) { this.artistID = artistID; }
    public void setAlbumID(UUID albumID) { this.albumID = albumID; }
    public String getTitle() { return title; }
    public UUID getArtistId() { return artistID; }
    public UUID getAlbumID() { return albumID; }
}
