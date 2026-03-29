package com.mangaverse.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDto {
    private int number;
    private String title;
    private List<String> imageUrls; // NEW: URLs of individual pages
}