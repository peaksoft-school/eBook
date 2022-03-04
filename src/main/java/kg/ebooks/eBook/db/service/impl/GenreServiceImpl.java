package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.dto.genre.GenreGetDTO;
import kg.ebooks.eBook.db.domain.dto.genre.GenreSV;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.GenreService;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import kg.ebooks.eBook.exceptions.ImpossibleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * created by Beksultan Mamatkadyr uulu
 * 27/1/22
 * Thursday 14:11
 * hello world
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<GenreDTO> findAllGenres() {
        List<Genre> genreList = genreRepository.findAll();
        log.info("founded {} genres from database", genreList.size());
        return genreList.stream()
                .map(genre -> modelMapper.map(genre, GenreDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GenreGetDTO findGenreById(Long genreId) {
        return findById(genreId);
    }

    private Genre findById(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> {
                    log.error("genre with id = {} not found ", genreId);
                    return new DoesNotExistsException(
                            "genre with id = " + genreId + " does not exists"
                    );
                });
    }

    @Override
    public GenreDTO saveGenre(GenreSV genre) {
        String genreName = genre.getGenreName();
        if (genreRepository.existsByGenreName(genreName)) {
            log.error("genre [{}] has already exists in database", genreName);
            throw new AlreadyExistsException(
                    "genre = " + genreName + " has already exists"
            );
        }
        log.info("saving new genre [{}] to database", genreName);
        return modelMapper.map(
                genreRepository.save(
                        modelMapper.map(genre, Genre.class)
                ), GenreDTO.class
        );
    }

    @Override
    public void deleteGenreById(Long genreId) {
        Genre byId = findById(genreId);
        try {
            genreRepository.delete(byId);
        } catch (Exception ex) {
            log.info("can't delete this genre with id = {} because this genre related to another table", genreId);
            throw new ImpossibleException(
                    "you can't delete this genre [" + byId.getGenreName() +
                            "] because this genre related to another table"
            );
        }
        log.info("deleted genre with id = {}", genreId);
    }

    @Override
    @Transactional
    public void updateGenre(Long genreId, GenreSV genre) {
        Genre genreById = findById(genreId);
        String genreName = genreById.getGenreName();
        String genreName1 = genre.getGenreName();
        if (!genreName1.isEmpty() &&
                !Objects.equals(genreName1, genreName)) {
            genreById.setGenreName(genreName1);
        }
        log.info("genre [{}] updated to [{}]", genreName, genreName1);
    }
}
