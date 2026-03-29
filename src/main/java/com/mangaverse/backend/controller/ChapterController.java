package com.mangaverse.backend.controller;

import com.mangaverse.backend.dto.ChapterDto;
import com.mangaverse.backend.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manga")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping("/{mangaId}/chapters")
    public ResponseEntity<?> addChapter(
            @PathVariable Long mangaId,
            @RequestBody ChapterDto dto) {
        ChapterDto saved = chapterService.addChapter(mangaId, dto);
        return ResponseEntity.ok(saved);
    }
}