package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

public class ArtistDTO {
    @NotNull
    private String name;

    public void setName(String name) { this.name = name; }
    public String getName() {
            return name;
    }
}
