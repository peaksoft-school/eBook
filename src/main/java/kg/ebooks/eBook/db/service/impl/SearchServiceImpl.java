package kg.ebooks.eBook.db.service.impl;

import com.google.common.collect.Lists;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.SearchDto;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    public List<SearchDto> findAll(String search) {
        List<SearchDto> all = new ArrayList<>();

        String finalSearch = search.toLowerCase();;


        repository.findAll().forEach(book -> {
            System.out.println(book);
            if (book.getBookName().toLowerCase().startsWith(finalSearch)) {
                all.add(new SearchDto(book.getBookId(), book.getBookName(), BOOK));
            }
            if (book.getAuthor().toLowerCase().startsWith(finalSearch)) {
                all.add(new SearchDto(book.getBookId(), book.getAuthor(), AUTHOR));
            }
            if ((!book.getTypeOfBook().equals(AUDIO_BOOK)) && book.getPublishingHouse().toLowerCase().startsWith(finalSearch)) {
                all.add(new SearchDto(book.getBookId(), book.getPublishingHouse(), BOOK));
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
