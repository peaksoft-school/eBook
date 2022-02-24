package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.book.BookResponseDTOSort;
import kg.ebooks.eBook.db.domain.dto.sort.Price;
import kg.ebooks.eBook.db.domain.dto.sort.SortRequest;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.SortService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 16:09
 * hello world
 */
@Service
@RequiredArgsConstructor
public class SortServiceImpl implements SortService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<BookResponseDTOSort> sort(SortRequest sortRequest) {
        return bookRepository.findAll().stream()
                .filter(book -> {
                    if (sortRequest.getGenres() == null){
                        return true;
                    }
                    if (sortRequest.getGenres().size() < 0) {
                        return true;
                    }
                    return filterA.test(book, sortRequest.getGenres());
                })
                .filter(book -> {
                    if (sortRequest.getTypeOfBook() == null) {
                        return true;
                    }
                    return book.getTypeOfBook().equals(sortRequest.getTypeOfBook());
                })
                .filter(book -> {
                    if (sortRequest.getPrice() == null) {
                        return true;
                    }
                    return filterC.test(book, sortRequest.getPrice());
                })
                .filter(book -> {
                    if (sortRequest.getLanguages() == null) {
                        return true;
                    }
                    if (sortRequest.getLanguages().size() < 0) {
                        return true;
                    }
                    return sortRequest.getLanguages().contains(book.getLanguage());
                })
                .map(book -> modelMapper.map(book, BookResponseDTOSort.class))
                .collect(Collectors.toList());
    }

    public BiPredicate<Book, List<Long>> filterA = (book, genres) -> genres.contains(book.getGenre().getId());
    public BiPredicate<Book, Price> filterC = (book, price) -> price.valid(book.getNetPrice());
}