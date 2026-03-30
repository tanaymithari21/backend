package com.mangaverse.backend.service;

import com.mangaverse.backend.dto.ChapterDto;
import com.mangaverse.backend.dto.ChapterPageDto;
import com.mangaverse.backend.dto.MangaDto;
import com.mangaverse.backend.model.Chapter;
import com.mangaverse.backend.model.ChapterPage;
import com.mangaverse.backend.model.Genre;
import com.mangaverse.backend.model.Manga;
import com.mangaverse.backend.model.Manga.Status;
import com.mangaverse.backend.repository.ChapterPageRepository;
import com.mangaverse.backend.repository.ChapterRepository;
import com.mangaverse.backend.repository.GenreRepository;
import com.mangaverse.backend.repository.MangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MangaService {

    private final MangaRepository mangaRepository;
    private final GenreRepository genreRepository;
    private final ChapterRepository chapterRepository;
    private final ChapterPageRepository chapterPageRepository;

    // ── Search / list ───────────────────────────────────────────────
    public Page<MangaDto> searchManga(String search, String genre, Pageable pageable) {
        return mangaRepository.searchManga(search, genre, pageable)
                .map(m -> toListDto(m));
    }

    // ── Get single ─────────────────────────────────────────────────
    public MangaDto getMangaById(Long id) {
        Manga manga = findManga(id);
        return toDetailDto(manga);
    }

    // ── Create ─────────────────────────────────────────────────────
    public MangaDto createManga(MangaDto dto) {
        Manga manga = new Manga();
        applyDtoToManga(dto, manga);
        manga.setRating(0.0);
        Manga saved = mangaRepository.save(manga);
        return toListDto(saved);
    }

    // ── Update manga details ────────────────────────────────────────
    @Transactional
    public MangaDto updateManga(Long id, MangaDto dto) {
        Manga manga = findManga(id);
        applyDtoToManga(dto, manga);
        // Only update cover if a new one is provided
        if (dto.getCover() != null && !dto.getCover().isBlank()) {
            manga.setCover(dto.getCover());
        }
        return toDetailDto(mangaRepository.save(manga));
    }

    // ── Delete manga — returns all Cloudinary URLs to delete on frontend ──
    @Transactional
    public Map<String, Object> deleteManga(Long id) {
        Manga manga = findManga(id);

        // Collect all image URLs for Cloudinary cleanup on frontend
        List<String> urlsToDelete = new ArrayList<>();
        if (manga.getCover() != null) urlsToDelete.add(manga.getCover());

        if (manga.getChapters() != null) {
            for (Chapter ch : manga.getChapters()) {
                if (ch.getPages() != null) {
                    ch.getPages().forEach(p -> {
                        if (p.getImageUrl() != null) urlsToDelete.add(p.getImageUrl());
                    });
                }
            }
        }

        mangaRepository.delete(manga);

        Map<String, Object> result = new HashMap<>();
        result.put("deleted", true);
        result.put("cloudinaryUrls", urlsToDelete); // frontend uses these to clean Cloudinary
        return result;
    }

    // ── Update chapter (title, number, reorder/add/remove pages) ───
    @Transactional
    public ChapterDto updateChapter(Long mangaId, Long chapterId, ChapterDto dto) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        chapter.setNumber(dto.getNumber());
        if (dto.getTitle() != null) chapter.setTitle(dto.getTitle());

        if (dto.getPages() != null) {
            // Find pages that were removed (present in DB, not in new list)
            Set<Long> keptIds = dto.getPages().stream()
                    .filter(p -> p.getId() != null)
                    .map(ChapterPageDto::getId)
                    .collect(Collectors.toSet());

            List<ChapterPage> removedPages = chapter.getPages().stream()
                    .filter(p -> !keptIds.contains(p.getId()))
                    .collect(Collectors.toList());

            // Collect removed URLs for Cloudinary cleanup response
            List<String> removedUrls = removedPages.stream()
                    .map(ChapterPage::getImageUrl)
                    .collect(Collectors.toList());

            // Delete removed pages from DB
            chapterPageRepository.deleteAll(removedPages);

            // Rebuild pages list with correct order
            List<ChapterPage> newPages = new ArrayList<>();
            for (int i = 0; i < dto.getPages().size(); i++) {
                ChapterPageDto pd = dto.getPages().get(i);
                ChapterPage cp;
                if (pd.getId() != null) {
                    // existing page — update page number (reorder)
                    cp = chapterPageRepository.findById(pd.getId())
                            .orElseThrow(() -> new RuntimeException("Page not found"));
                    cp.setPageNumber(i + 1);
                } else {
                    // new page — create
                    cp = new ChapterPage();
                    cp.setChapter(chapter);
                    cp.setImageUrl(pd.getImageUrl());
                    cp.setPageNumber(i + 1);
                }
                newPages.add(cp);
            }
            chapterPageRepository.saveAll(newPages);
            chapter.setPages(newPages);
        }

        chapterRepository.save(chapter);

        // Collect removed URLs to return so frontend can clean Cloudinary
        List<String> removedUrls = dto.getPages() != null
                ? chapter.getPages().stream()
                        .filter(p -> dto.getPages().stream().noneMatch(dp -> p.getId() != null && p.getId().equals(dp.getId())))
                        .map(ChapterPage::getImageUrl)
                        .collect(Collectors.toList())
                : List.of();

        ChapterDto result = new ChapterDto();
        result.setId(chapter.getId());
        result.setNumber(chapter.getNumber());
        result.setTitle(chapter.getTitle());
        result.setImageUrls(chapter.getPages().stream().map(ChapterPage::getImageUrl).collect(Collectors.toList()));
        return result;
    }

    // ── Delete chapter — returns all page URLs for Cloudinary cleanup ──
    @Transactional
    public Map<String, Object> deleteChapter(Long mangaId, Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        List<String> urlsToDelete = chapter.getPages() != null
                ? chapter.getPages().stream().map(ChapterPage::getImageUrl).collect(Collectors.toList())
                : List.of();

        chapterRepository.delete(chapter);

        Map<String, Object> result = new HashMap<>();
        result.put("deleted", true);
        result.put("cloudinaryUrls", urlsToDelete);
        return result;
    }

    // ── Helpers ─────────────────────────────────────────────────────
    private Manga findManga(Long id) {
        return mangaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manga not found: " + id));
    }

    private void applyDtoToManga(MangaDto dto, Manga manga) {
        if (dto.getTitle() != null)       manga.setTitle(dto.getTitle());
        if (dto.getAuthor() != null)      manga.setAuthor(dto.getAuthor());
        if (dto.getDescription() != null) manga.setDescription(dto.getDescription());
        if (dto.getYear() > 0)            manga.setYear(dto.getYear());
        if (dto.getStatus() != null)      manga.setStatus(Status.fromString(dto.getStatus()));
        if (dto.getCover() != null && !dto.getCover().isBlank()) manga.setCover(dto.getCover());

        if (dto.getGenres() != null) {
            List<Genre> genres = dto.getGenres().stream()
                    .map(name -> genreRepository.findByName(name)
                            .orElseGet(() -> genreRepository.save(new Genre(name))))
                    .collect(Collectors.toList());
            manga.setGenres(genres);
        }
    }

    private MangaDto toListDto(Manga m) {
        return new MangaDto(
                m.getId(), m.getTitle(), m.getCover(), m.getRating(),
                m.getStatus() != null ? m.getStatus().name() : null,
                m.getAuthor(), m.getDescription(), m.getYear(),
                m.getGenres() != null ? m.getGenres().stream().map(Genre::getName).collect(Collectors.toList()) : null,
                m.getChapters() != null ? m.getChapters().size() : 0,
                null);
    }

    private MangaDto toDetailDto(Manga m) {
        return new MangaDto(
                m.getId(), m.getTitle(), m.getCover(), m.getRating(),
                m.getStatus() != null ? m.getStatus().name() : null,
                m.getAuthor(), m.getDescription(), m.getYear(),
                m.getGenres() != null ? m.getGenres().stream().map(Genre::getName).collect(Collectors.toList()) : null,
                m.getChapters() != null ? m.getChapters().size() : 0,
                m.getChapters() != null
                        ? m.getChapters().stream().map(ch -> {
                            ChapterDto cdto = new ChapterDto();
                            cdto.setId(ch.getId());
                            cdto.setNumber(ch.getNumber());
                            cdto.setTitle(ch.getTitle());
                            cdto.setImageUrls(ch.getPages() != null
                                    ? ch.getPages().stream().map(ChapterPage::getImageUrl).collect(Collectors.toList())
                                    : List.of());
                            cdto.setPages(ch.getPages() != null
                                    ? ch.getPages().stream()
                                            .map(p -> new ChapterPageDto(p.getId(), p.getPageNumber(), p.getImageUrl()))
                                            .collect(Collectors.toList())
                                    : List.of());
                            return cdto;
                        }).collect(Collectors.toList())
                        : null);
    }
}
