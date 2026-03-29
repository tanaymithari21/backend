package com.mangaverse.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ChapterPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pageNumber;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}