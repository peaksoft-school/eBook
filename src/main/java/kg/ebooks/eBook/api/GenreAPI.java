package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.dto.genre.GenreGetDTO;
import kg.ebooks.eBook.db.domain.dto.genre.GenreSV;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 03:37
 */

@RestController
@RequestMapping("api/genres")
@CrossOrigin
@RequiredArgsConstructor
public class GenreAPI {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDTO> findAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/get/{genreId}")
    public GenreGetDTO findGenreById(@PathVariable Long genreId) {
        return genreService.findGenreById(genreId);
    }

    @PostMapping("/save")
    public GenreDTO saveGenre(@RequestBody GenreSV genre) {
        return genreService.saveGenre(genre);
    }

    @DeleteMapping("/delete/{genreId}")
    public void deleteGenreById(@PathVariable Long genreId) {
        genreService.deleteGenreById(genreId);
    }

    @PatchMapping("/update/{genreId}")
    public void updateGenre(@PathVariable Long genreId,
                            @RequestBody GenreSV genre) {
        genreService.updateGenre(genreId, genre);
    }
}
