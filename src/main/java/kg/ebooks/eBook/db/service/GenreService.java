package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.dto.genre.GenreGetDTO;
import kg.ebooks.eBook.db.domain.dto.genre.GenreSV;
import kg.ebooks.eBook.db.domain.model.others.Genre;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 27/1/22
 * Thursday 14:11
 * hello world
 */
public interface GenreService {
    List<GenreDTO> findAllGenres();

    GenreGetDTO findGenreById(Long genreId);

    GenreDTO saveGenre(GenreSV genre);

    void deleteGenreById(Long genreId);

    void updateGenre(Long genreId, GenreSV genre);
}
