package kg.ebooks.eBook.db.service.impl;

import com.google.common.collect.Lists;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.SearchDto;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.*;
import static kg.ebooks.eBook.db.domain.model.enums.Type.*;
import static kg.ebooks.eBook.db.domain.model.enums.TypeOfBook.*;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final BookRepository repository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    public Set<SearchDto> findAll(String search) {
        Set<SearchDto> all = new HashSet<>();

        String finalSearch = search.toLowerCase();;

        repository.findAll().forEach(book -> {
            if (book.getBookName().toLowerCase().startsWith(finalSearch)) {
                all.add(new SearchDto(book.getBookId(), book.getBookName(), BOOK));
            }
            if (book.getAuthor().toLowerCase().startsWith(finalSearch)) {
                all.add(new SearchDto(book.getBookId(), book.getAuthor(), AUTHOR));
            }
            if ((!book.getTypeOfBook().equals(AUDIO_BOOK)) && book.getPublishingHouse().toLowerCase().startsWith(finalSearch)) {
                all.add(new SearchDto(book.getBookId(), book.getPublishingHouse(), PUBLISHER));
            }
        });

        genreRepository.findAll().forEach(genre -> {
            if (genre.getGenreName().toLowerCase().startsWith(finalSearch)) {
                all.add(new SearchDto(genre.getId(), genre.getGenreName(), GENRE));
            }
        });

        return all;
    }

    @Override
    public Set<BookResponse> findByAuthorName(String authorName) {
        return repository.findByAuthor(authorName)
                .stream()
                .filter(book -> book.getRequestStatus().equals(ACCEPTED))
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<BookResponse> findByPublishingHouse(String publisher) {
        return repository.findElectronicAndPaperBooks(Lists.newArrayList(PAPER_BOOK, AUDIO_BOOK))
                .stream().filter(book -> book.getRequestStatus().equals(ACCEPTED))
                .filter(book -> Objects.equals(book.getPublishingHouse(), publisher))
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toSet());
    }
}
