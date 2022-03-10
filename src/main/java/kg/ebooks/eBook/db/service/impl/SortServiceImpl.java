package kg.ebooks.eBook.db.service.impl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.BookResponseDTOSort;
import kg.ebooks.eBook.db.domain.dto.sort.*;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.SortService;
import kg.ebooks.eBook.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
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
    public List<BookResponseDTOSort> sort(SortRequest sortRequest) {
        return bookRepository.findAll().stream()
                .filter(book -> {
                    if (sortRequest.getGenres() == null) {
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
                .map(book -> book != null ? modelMapper.map(book, BookResponseDTOSort.class) : null)
                .collect(Collectors.toList());
    }

    public BiPredicate<Book, List<Long>> filterA = (book, genres) -> genres.contains(book.getGenre().getId());
    public BiPredicate<Book, Price> filterC = (book, price) -> price.valid(book.getNetPrice());

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

    private final XStream xStream = new XStream(new JsonHierarchicalStreamDriver());

    @Override
    public List<BookResponseDTOSort> sort(String filterBy, String sortBy) {
//        try {
        FilterBy filter = (FilterBy) xStream.fromXML(filterBy);
        SortBy sort = (SortBy) xStream.fromXML(sortBy);
//        }

        System.out.println("+=============================================+");
        System.out.println(filter);
        System.out.println(sort);
        System.out.println("+=============================================+");
        return null;
    }
}
