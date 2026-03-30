package com.mangaverse.backend.controller;

import com.mangaverse.backend.model.Genre;
import com.mangaverse.backend.model.Manga;
import com.mangaverse.backend.repository.GenreRepository;
import com.mangaverse.backend.repository.MangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;
    private final MangaRepository mangaRepository;

    // GET /api/genres — plain string list (used by filters & upload forms)
    @GetMapping
    public List<String> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(Genre::getName).sorted().collect(Collectors.toList());
    }

    // GET /api/genres/full — id + name (used by GenreManager page)
    @GetMapping("/full")
    public List<Map<String, Object>> getAllGenresFull() {
        return genreRepository.findAll().stream()
                .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                .map(g -> Map.<String, Object>of("id", g.getId(), "name", g.getName()))
                .collect(Collectors.toList());
    }

    // POST /api/genres  body: { "name": "Isekai" }
    @PostMapping
    public ResponseEntity<?> addGenre(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        if (name == null || name.isBlank())
            return ResponseEntity.badRequest().body("Name is required");
        if (genreRepository.findByName(name.trim()).isPresent())
            return ResponseEntity.badRequest().body("Genre already exists");
        Genre saved = genreRepository.save(new Genre(name.trim()));
        return ResponseEntity.ok(Map.of("id", saved.getId(), "name", saved.getName()));
    }

    // PUT /api/genres/{id}  body: { "name": "New Name" }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> renameGenre(@PathVariable Long id,
                                          @RequestBody Map<String, String> body) {
        String newName = body.get("name");
        if (newName == null || newName.isBlank())
            return ResponseEntity.badRequest().body("Name is required");
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        genre.setName(newName.trim());
        genreRepository.save(genre);
        return ResponseEntity.ok(Map.of("id", genre.getId(), "name", genre.getName()));
    }

    // DELETE /api/genres/{id} — removes genre AND unlinks it from every manga
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        List<Manga> affected = mangaRepository.findAll().stream()
                .filter(m -> m.getGenres() != null &&
                        m.getGenres().stream().anyMatch(g -> g.getId().equals(id)))
                .collect(Collectors.toList());

        for (Manga manga : affected) {
            manga.getGenres().removeIf(g -> g.getId().equals(id));
            mangaRepository.save(manga);
        }

        genreRepository.delete(genre);

        return ResponseEntity.ok(Map.of(
                "deleted", true,
                "removedFromMangaCount", affected.size()
        ));
    }
}
