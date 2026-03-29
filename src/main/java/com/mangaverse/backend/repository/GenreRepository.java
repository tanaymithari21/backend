package com.mangaverse.backend.repository;

import com.mangaverse.backend.model.Genre;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name); // ✅ must return Optional
}