package com.mangaverse.backend.repository;

import com.mangaverse.backend.model.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {

    // Search by title or author (case-insensitive), optionally filter by genre name
    @Query("""
        SELECT DISTINCT m FROM Manga m
        LEFT JOIN m.genres g
        WHERE
            (:search IS NULL OR :search = '' OR
                LOWER(m.title) LIKE LOWER(CONCAT('%', :search, '%')) OR
                LOWER(m.author) LIKE LOWER(CONCAT('%', :search, '%')))
            AND
            (:genre IS NULL OR :genre = '' OR g.name = :genre)
    """)
    Page<Manga> searchManga(
            @Param("search") String search,
            @Param("genre") String genre,
            Pageable pageable);
}
