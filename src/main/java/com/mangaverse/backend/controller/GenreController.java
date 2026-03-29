package com.mangaverse.backend.controller;

import com.mangaverse.backend.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;

    @GetMapping
    public List<String> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(g -> g.getName())
                .sorted()
                .collect(Collectors.toList());
    }
}
