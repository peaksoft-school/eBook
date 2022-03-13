package kg.ebooks.eBook.db.service.impl;

import com.google.gson.Gson;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.BookResponseDTOSort;
import kg.ebooks.eBook.db.domain.dto.sort.*;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.SortService;
import kg.ebooks.eBook.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import static kg.ebooks.eBook.db.domain.model.enums.TypeOfBook.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 16:09
 * hello world
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SortServiceImpl implements SortService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Override
    public List<BookResponse> findAllByType(String type) {
        switch (type) {
            case "paperBook":
                return bookRepository.findByBookType(PAPER_BOOK)
                        .stream()
                        .map(book -> modelMapper.map(book, BookResponse.class))
                        .collect(Collectors.toList());
            case "audioBook":
                return bookRepository.findByBookType(AUDIO_BOOK)
                        .stream()
                        .map(book -> modelMapper.map(book, BookResponse.class))
                        .collect(Collectors.toList());
            case "electronicBook":
                return bookRepository.findByBookType(ELECTRONIC_BOOK)
                        .stream()
                        .map(book -> modelMapper.map(book, BookResponse.class))
                        .collect(Collectors.toList());
            default:
                throw new InvalidRequestException(
                        "you give wrong type of book [" + type + "]"
                );
        }
    }

    public BiPredicate<Book, Set<Long>> filterA = (book, genres) -> genres.contains(book.getGenre().getId());
    public BiPredicate<Book, Price> filterC = (book, price) -> price.valid(book.getNetPrice());

    @Override
    public Set<BookResponseDTOSort> sort(String filterBy) {

        final FilterBy filter;
        try {
            log.info("filterBy = {}", filterBy );
            filter = gson.fromJson(filterBy, FilterBy.class);
        } catch (Exception exception) {
            throw new InvalidRequestException(
                    "Invalid JSON type + " + filterBy
            );
        }

        fillIfNullOrEmpty(filter);

        log.info("filterBy = {}", filter);
        Set<Book> bookSet = bookRepository.findByGenreId(filter.getGenres(), filter.getLanguages());
        return bookSet.stream()
                .filter(book -> {
                    assert filter.getPrice() != null;
                    return filter.getPrice().valid(book.getPrice());
                })
                .filter(book -> {
                    if (filter.getTypeOfBook() == null) return true;
                    return filter.getTypeOfBook().equals(book.getTypeOfBook());
                })
                .map(book -> modelMapper.map(book, BookResponseDTOSort.class))
                .collect(Collectors.toSet());
    }

    private void fillIfNullOrEmpty(FilterBy filter) {
        if (filter.getGenres() == null || filter.getGenres().size() == 0) {
            filter.setGenres(genreRepository.findAll().stream().map(Genre::getId).collect(Collectors.toSet()));
        } else if (filter.getLanguages() == null || filter.getLanguages().size() == 0) {
            filter.setLanguages(new HashSet<>(Arrays.asList(Language.values())));
        }
    }

}
