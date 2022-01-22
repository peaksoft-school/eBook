package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.GenreService;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import kg.ebooks.eBook.exceptions.GenreNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 03:08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        log.info("#GET REQUEST: founded {} books", genres.size());
        return genres;
    }

    @Override
    public Genre findGenreById(Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> {
                    log.error("#GET REQUEST: genre with id = {} does not exists", genreId);
                    throw new GenreNotFound(
                            "genre with id = " + genreId + " does not exists"
                    );
                });
        log.info("#GET REQUEST: founded genre with id = {}", genreId);
        return genre;
    }

    @Override
    public Genre saveGenre(Genre genre) {
        boolean exists = genreRepository.existsByGenreName(genre.getGenreName());
        if (exists) {
            log.error("#POST REQUEST: genre with genre name = {} has already exists", genre.getGenreName() );
            throw new AlreadyExistsException(
                    String.format("genre (%s) has already exists", genre.getGenreName())
            );
        }
        log.info("#POST REQUEST: genre successfully save to database");
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenreById(Long genreId) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists) {
            log.error("#DELETE REQUEST: genre with id = {} does not exists", genreId);
            throw new DoesNotExistsException(
                    String.format("Genre with id = %d does not exists", genreId)
            );
        }
        log.info("#DELETE REQUEST: genre with id = {} successfully deleted", genreId);
        genreRepository.deleteById(genreId);
    }

    @Override
    @Transactional
    public void updateGenre(Long genreId, Genre newGenre) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> {
                    log.error("genre with id = {} does not exists", genreId);
                   throw new DoesNotExistsException(
                        String.format("Genre with id = %d does not exists", genreId)
                );});
        String genreName = newGenre.getGenreName();
        if (genreName != null && genreName.length() > 0
                && !Objects.equals(genreName, genre.getGenreName())) {
            genre.setGenreName(genreName);
        }

        Integer quantityOfBooks = newGenre.getQuantityOfBooks();
        if (quantityOfBooks != null && quantityOfBooks > 0 &&
                !Objects.equals(quantityOfBooks, genre.getQuantityOfBooks())) {
            genre.setQuantityOfBooks(quantityOfBooks);
        }
        log.info("genre with id = {} successfully updated", genreId);
    }
}
