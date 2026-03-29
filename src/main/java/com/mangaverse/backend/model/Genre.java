package com.mangaverse.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genre")
@Data
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Fix: needed by MangaService → genreRepository.save(new Genre(name))
    public Genre(String name) {
        this.name = name;
    }
}
