package com.mangaverse.backend.controller;

import com.mangaverse.backend.dto.ChapterDto;
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

    @PostMapping
    public ResponseEntity<MangaDto> createManga(@RequestBody MangaDto dto) {
        return ResponseEntity.ok(mangaService.createManga(dto));
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<MangaDto> getMangaById(@PathVariable Long id) {
        return ResponseEntity.ok(mangaService.getMangaById(id));
    }

    // ── Edit manga details ──────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<MangaDto> updateManga(
            @PathVariable Long id,
            @RequestBody MangaDto dto) {
        return ResponseEntity.ok(mangaService.updateManga(id, dto));
    }

    // ── Delete manga + all chapters/pages (Cloudinary cleanup on frontend) ──
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteManga(@PathVariable Long id) {
        Map<String, Object> result = mangaService.deleteManga(id);
        return ResponseEntity.ok(result);
    }

    // ── Edit chapter (title, number, reorder/add/remove pages) ─────
    @PutMapping("/{mangaId}/chapters/{chapterId}")
    public ResponseEntity<ChapterDto> updateChapter(
            @PathVariable Long mangaId,
            @PathVariable Long chapterId,
            @RequestBody ChapterDto dto) {
        return ResponseEntity.ok(mangaService.updateChapter(mangaId, chapterId, dto));
    }

    // ── Delete chapter + pages (Cloudinary cleanup on frontend) ────
    @DeleteMapping("/{mangaId}/chapters/{chapterId}")
    public ResponseEntity<Map<String, Object>> deleteChapter(
            @PathVariable Long mangaId,
            @PathVariable Long chapterId) {
        Map<String, Object> result = mangaService.deleteChapter(mangaId, chapterId);
        return ResponseEntity.ok(result);
    }
}
