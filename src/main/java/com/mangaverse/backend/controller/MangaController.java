// package com.mangaverse.backend.repository;

// import com.mangaverse.backend.model.Manga;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/manga")
// @RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:5173") // Adjust if your frontend runs on a different port
// public class MangaController {

//     private final MangaRepository mangaRepository;
//     // private final GenreRepository genreRepository;

//     @GetMapping
//     public List<MangaDTO> getMangas(
//             @RequestParam(required = false) String search,
//             @RequestParam(required = false) String genre) {
//         List<Manga> mangas = mangaRepository.findAll();

//         // Filter by search
//         if (search != null && !search.isEmpty()) {
//             String lowerSearch = search.toLowerCase();
//             mangas = mangas.stream()
//                     .filter(m -> m.getTitle().toLowerCase().contains(lowerSearch)
//                             || m.getAuthor().toLowerCase().contains(lowerSearch))
//                     .collect(Collectors.toList());
//         }

//         // Filter by genre
//         if (genre != null && !genre.equalsIgnoreCase("All")) {
//             mangas = mangas.stream()
//                     .filter(m -> m.getGenres().stream().anyMatch(g -> g.getName().equalsIgnoreCase(genre)))
//                     .collect(Collectors.toList());
//         }

//         // Convert to DTO for frontend
//         return mangas.stream().map(MangaDTO::fromEntity).collect(Collectors.toList());
//     }

//     // DTO to avoid sending unnecessary data like PDF URLs
//     @Data
//     @AllArgsConstructor
//     @NoArgsConstructor
//     static class MangaDTO {
//         private Long id;
//         private String title;
//         private String cover;
//         private Double rating;
//         private String status;
//         private List<String> genres;
//         private int chaptersCount;

//         static MangaDTO fromEntity(Manga manga) {
//             return new MangaDTO();
//         }
//     }
// }

package com.mangaverse.backend.controller;

import com.mangaverse.backend.dto.MangaDto;
import com.mangaverse.backend.repository.MangaRepository;
import com.mangaverse.backend.service.MangaService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manga")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // Adjust if your frontend runs on a different port
public class MangaController {

    private final MangaService mangaService;
    private final MangaRepository mangaRepository;

    @PostMapping
    public ResponseEntity<MangaDto> createManga(@RequestBody MangaDto dto) {
        MangaDto saved = mangaService.createManga(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<MangaDto> getAllManga() {
        return mangaService.getAllManga();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaDto> getMangaById(@PathVariable Long id) {
        return ResponseEntity.ok(mangaService.getMangaById(id));
    }
}