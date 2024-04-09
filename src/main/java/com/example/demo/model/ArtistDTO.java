package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ArtistDTO {
    private UUID artistID;
    @NotNull
    private String name;


    public void setName(String name) { this.name = name; }
    public void setArtistID(UUID artistID) { this.artistID = artistID; }
    public String getName() {
            return name;
    }
    public UUID getArtistID() { return artistID; }
}
