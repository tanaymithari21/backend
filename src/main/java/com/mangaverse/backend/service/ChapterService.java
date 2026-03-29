package com.mangaverse.backend.service;

import com.mangaverse.backend.dto.ChapterDto;
import com.mangaverse.backend.model.Chapter;
import com.mangaverse.backend.model.ChapterPage;
import com.mangaverse.backend.model.Manga;
import com.mangaverse.backend.repository.ChapterPageRepository;
import com.mangaverse.backend.repository.ChapterRepository;
import com.mangaverse.backend.repository.MangaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final ChapterPageRepository chapterPageRepository;
    private final MangaRepository mangaRepository;

    public ChapterDto addChapter(Long mangaId, ChapterDto dto) {
        Manga manga = mangaRepository.findById(mangaId)
                .orElseThrow(() -> new RuntimeException("Manga not found"));

        Chapter chapter = new Chapter();
        chapter.setManga(manga);
        chapter.setNumber(dto.getNumber());
        chapter.setTitle(dto.getTitle());
        chapterRepository.save(chapter);

        List<String> imageUrls = dto.getImageUrls();
        for (int i = 0; i < imageUrls.size(); i++) {
            ChapterPage page = new ChapterPage();
            page.setChapter(chapter);
            page.setPageNumber(i + 1);
            page.setImageUrl(imageUrls.get(i));
            chapterPageRepository.save(page);
        }

        ChapterDto savedDto = new ChapterDto();
        savedDto.setNumber(chapter.getNumber());
        savedDto.setTitle(chapter.getTitle());
        savedDto.setImageUrls(imageUrls);
        return savedDto;
    }
}