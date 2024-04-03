package com.example.demo.model;

import java.util.UUID;

public class SongDTO {
    private String title;
    private UUID albumID;

    public void setTitle(String title) { this.title = title; }
    public void setAlbumID(UUID albumID) { this.albumID = albumID; }
    public String getTitle() { return title; }
    public UUID getAlbumID() { return albumID; }
}
