package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class SongDTO {
    @NotNull
    private String title;
    @NotNull
    private UUID albumID;

    public void setTitle(String title) { this.title = title; }
    public void setAlbumID(UUID albumID) { this.albumID = albumID; }
    public String getTitle() { return title; }
    public UUID getAlbumID() { return albumID; }
}
