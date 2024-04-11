package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AlbumDTO {
    private UUID albumID;
    @NotNull
    private String title;
    @NotNull
    private UUID artistID;
}
