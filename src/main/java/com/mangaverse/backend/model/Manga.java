package com.mangaverse.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "manga")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String cover;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double rating;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String author;

    private int year;

    // 🔥 One Manga → Many Chapters
    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters;

    // 🔥 Many-to-Many with Genre
    @ManyToMany
    @JoinTable(name = "manga_genre", joinColumns = @JoinColumn(name = "manga_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    public enum Status {
        Ongoing,
        Completed;

        public static Status fromString(String s) {
            if (s == null)
                return null;
            for (Status status : Status.values()) {
                if (status.name().equalsIgnoreCase(s))
                    return status;
            }
            throw new IllegalArgumentException("Unknown status: " + s);
        }
    }
}
