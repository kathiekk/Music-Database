package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class SongDTO {
    private UUID songID;
    @NotNull
    private String title;
    @NotNull
    private UUID albumID;

    public void setTitle(String title) { this.title = title; }
    public void setAlbumID(UUID albumID) { this.albumID = albumID; }
    public void setSongID(UUID songID) { this.songID = songID; }
    public String getTitle() { return title; }
    public UUID getAlbumID() { return albumID; }
    public UUID getSongID() { return songID; }
}
