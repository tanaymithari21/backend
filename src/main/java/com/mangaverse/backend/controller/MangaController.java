package com.mangaverse.backend.controller;

import com.mangaverse.backend.dto.MangaDto;
import com.mangaverse.backend.repository.MangaRepository;
import com.mangaverse.backend.service.MangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manga")
@RequiredArgsConstructor
public class MangaController {

    private final MangaService mangaService;
    private final MangaRepository mangaRepository;

    // POST /api/manga — create manga
    @PostMapping
    public ResponseEntity<MangaDto> createManga(@RequestBody MangaDto dto) {
        MangaDto saved = mangaService.createManga(dto);
        return ResponseEntity.ok(saved);
    }

    // GET /api/manga?page=0&size=10&search=naruto&genre=Action
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllManga(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String genre) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MangaDto> result = mangaService.searchManga(search, genre, pageable);

        return ResponseEntity.ok(Map.of(
                "content", result.getContent(),
                "totalPages", result.getTotalPages(),
                "totalElements", result.getTotalElements(),
                "currentPage", result.getNumber(),
                "hasNext", result.hasNext()
        ));
    }

    // GET /api/manga/{id} — single manga detail with chapters
    @GetMapping("/{id}")
    public ResponseEntity<MangaDto> getMangaById(@PathVariable Long id) {
        return ResponseEntity.ok(mangaService.getMangaById(id));
    }
}
