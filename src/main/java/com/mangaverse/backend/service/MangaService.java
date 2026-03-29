package com.mangaverse.backend.service;

import com.mangaverse.backend.dto.ChapterDto;
import com.mangaverse.backend.dto.MangaDto;
import com.mangaverse.backend.model.Genre;
import com.mangaverse.backend.model.Manga;
import com.mangaverse.backend.model.Manga.Status;
import com.mangaverse.backend.repository.GenreRepository;
import com.mangaverse.backend.repository.MangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MangaService {

    private final MangaRepository mangaRepository;
    private final GenreRepository genreRepository;

    // Paginated search by title/author and optional genre filter
    public Page<MangaDto> searchManga(String search, String genre, Pageable pageable) {
        return mangaRepository.searchManga(search, genre, pageable)
                .map(m -> new MangaDto(
                        m.getId(),
                        m.getTitle(),
                        m.getCover(),
                        m.getRating(),
                        m.getStatus() != null ? m.getStatus().name() : null,
                        m.getAuthor(),
                        m.getDescription(),
                        m.getYear(),
                        m.getGenres() != null
                                ? m.getGenres().stream().map(Genre::getName).collect(Collectors.toList())
                                : null,
                        m.getChapters() != null ? m.getChapters().size() : 0,
                        null // no chapter detail needed for list view
                ));
    }

    public MangaDto getMangaById(Long id) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manga not found"));

        return new MangaDto(
                manga.getId(),
                manga.getTitle(),
                manga.getCover(),
                manga.getRating(),
                manga.getStatus() != null ? manga.getStatus().name() : null,
                manga.getAuthor(),
                manga.getDescription(),
                manga.getYear(),
                manga.getGenres() != null
                        ? manga.getGenres().stream().map(Genre::getName).collect(Collectors.toList())
                        : null,
                manga.getChapters() != null ? manga.getChapters().size() : 0,
                manga.getChapters() != null
                        ? manga.getChapters().stream()
                                .map(ch -> new ChapterDto(ch.getNumber(), ch.getTitle(),
                                        ch.getPages().stream()
                                                .map(p -> p.getImageUrl())
                                                .collect(Collectors.toList())))
                                .collect(Collectors.toList())
                        : null);
    }

    public MangaDto createManga(MangaDto dto) {
        Manga manga = new Manga();
        manga.setTitle(dto.getTitle());
        manga.setAuthor(dto.getAuthor());
        manga.setDescription(dto.getDescription());
        manga.setYear(dto.getYear());
        manga.setCover(dto.getCover());
        manga.setRating(0.0);

        if (dto.getStatus() != null) {
            manga.setStatus(Status.fromString(dto.getStatus()));
        }

        if (dto.getGenres() != null) {
            List<Genre> genres = dto.getGenres().stream()
                    .map(name -> genreRepository.findByName(name)
                            .orElseGet(() -> genreRepository.save(new Genre(name))))
                    .collect(Collectors.toList());
            manga.setGenres(genres);
        }

        Manga saved = mangaRepository.save(manga);

        return new MangaDto(
                saved.getId(),
                saved.getTitle(),
                saved.getCover(),
                saved.getRating(),
                saved.getStatus().name(),
                saved.getAuthor(),
                saved.getDescription(),
                saved.getYear(),
                dto.getGenres(),
                0,
                null);
    }
}
