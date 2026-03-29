package com.mangaverse.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MangaDto {
    private Long id;
    private String title;
    private String cover;
    private double rating;
    private String status;
    private String author; // ✅ add this
    private String description; // ✅ add this
    private int year; // ✅ add this
    private List<String> genres;
    private int chaptersCount;
    private List<ChapterDto> chapters;
}