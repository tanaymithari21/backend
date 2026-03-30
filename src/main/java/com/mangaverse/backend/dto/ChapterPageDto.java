package com.mangaverse.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterPageDto {
    private Long id;          // null = new page, non-null = existing page
    private int pageNumber;
    private String imageUrl;
}
