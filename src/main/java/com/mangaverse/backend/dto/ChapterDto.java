package com.mangaverse.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDto {
    private Long id;           // chapter DB id (needed for edit/delete)
    private int number;
    private String title;
    private List<String> imageUrls;  // kept for upload compat
    private List<ChapterPageDto> pages; // used for edit (includes page id)

    // Backward-compat constructor used by existing upload code
    public ChapterDto(int number, String title, List<String> imageUrls) {
        this.number = number;
        this.title = title;
        this.imageUrls = imageUrls;
    }
}
