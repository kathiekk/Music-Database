package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String title;

    @ManyToOne
    private Album album;

    public UUID getAlbumID() { return album.getId(); }

    public SongDTO toDTO() {
        SongDTO songDTO = new SongDTO();
        songDTO.setTitle(title);
        songDTO.setAlbumID(getAlbumID());
        songDTO.setSongID(id);
        return songDTO;
    }
}
