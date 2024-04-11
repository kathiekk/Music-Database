package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String title;

    @ManyToOne
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;

    public UUID getArtistID() { return artist.getId();}

    public AlbumDTO toDTO() {
        AlbumDTO result = new AlbumDTO();
        result.setTitle(title);
        result.setArtistID(getArtistID());
        result.setAlbumID(id);
        return result;
    }
}
