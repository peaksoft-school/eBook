package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.model.others.Genre;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 03:05
 */
public interface GenreService {

    List<Genre> findAllGenres();

    Genre findGenreById(Long genreId);

    Genre saveGenre(Genre genre);

    void deleteGenreById(Long genreId);

    void updateGenre(Long genreId, Genre newGenre);

}
