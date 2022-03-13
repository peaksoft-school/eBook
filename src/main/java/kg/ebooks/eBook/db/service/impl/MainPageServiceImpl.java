package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.book.BookLatestPublication;
import kg.ebooks.eBook.db.domain.dto.book.BookMainPage;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.dto.main.MainPageRequest;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.dto.main.MainPageRequest.BESTSELLER;
import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.*;
import static kg.ebooks.eBook.db.domain.model.enums.TypeOfBook.*;

/**
 * @author Beksultan
 */
@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;

    @Override
    public List<BookMainPage> findByRequest(MainPageRequest request) {
        Random random = new Random();
        switch (request) {
            case ELECTRONIC:
                return bookRepository.findTheMostPopularBooks(ELECTRONIC_BOOK, ACCEPTED)
                        .stream().limit(5)
                        .map(book -> modelMapper.map(book, BookResponse.class))
                        .collect(Collectors.toList());
            case AUDIO:
                return bookRepository.findTheMostPopularBooks(AUDIO_BOOK, ACCEPTED)
                        .stream().limit(3)
                        .map(book -> modelMapper.map(book, BookResponse.class))
                        .collect(Collectors.toList());
            case LAST:
                List<Genre> all = genreRepository.findAll();
                if (all.size() <= 6) {
                    return all.stream().map(genre -> new BookLatestPublication(
                            modelMapper.map(genre, GenreDTO.class),
                            modelMapper.map(genreRepository.findLastBook(genre.getId()).get(0), BookResponse.class)))
                            .collect(Collectors.toList());
                }
            case BESTSELLER:
                return bookRepository.findBestSellerBooks(ACCEPTED)
                        .stream().limit(5)
                        .map(book -> modelMapper.map(book, BookResponse.class))
                        .collect(Collectors.toList());
            case THE_MOST_POPULAR:
                return bookRepository.findPopularBooks(ACCEPTED)
                        .stream().limit(10)
                        .filter(Book::isNew)
                        .limit(3)
                        .map(book -> modelMapper.map(book, BookResponse.class))
                        .collect(Collectors.toList());
        }
        return null;
    }
}
