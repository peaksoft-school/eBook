package kg.ebooks.eBook.db.service.impl;

import com.google.gson.Gson;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.BookResponseDTOSort;
import kg.ebooks.eBook.db.domain.dto.sort.*;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.SortService;
import kg.ebooks.eBook.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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
    private final ModelMapper modelMapper;

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
            Gson gson = new Gson();
            log.info("filterBy = {}", filterBy );
            filter = gson.fromJson(filterBy, FilterBy.class);
        } catch (Exception exception) {
            throw new InvalidRequestException(
                    "Invalid JSON type + " + filterBy
            );
        }

        log.info("filterBy = {}", filter);

        return bookRepository.findAll().stream()
                .filter(book -> {
                    if (filter.getGenres() == null) {
                        return true;
                    }
                    if (filter.getGenres().size() == 0) {
                        return true;
                    }
                    boolean test = filterA.test(book, filter.getGenres());
                    System.out.println(test);
                    return test;
                })
                .filter(book -> {
                    if (filter.getTypeOfBook() == null) {
                        return true;
                    }
                    return book.getTypeOfBook().equals(filter.getTypeOfBook());
                })
                .filter(book -> {
                    if (filter.getPrice() == null) {
                        return true;
                    }
                    return filterC.test(book, filter.getPrice());
                })
                .filter(book -> {
                    if (filter.getLanguages() == null) {
                        return true;
                    }
                    if (filter.getLanguages().size() == 0) {
                        return true;
                    }
                    return filter.getLanguages().contains(book.getLanguage());
                })
                .map(book -> book != null ? modelMapper.map(book, BookResponseDTOSort.class) : null)
                .collect(Collectors.toSet());
    }

}
