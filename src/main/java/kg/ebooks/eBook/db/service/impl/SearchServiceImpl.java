package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.book.SearchDto;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static kg.ebooks.eBook.db.domain.model.enums.Type.*;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final BookRepository repository;
    private final GenreRepository genreRepository;

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
            if ((!book.getTypeOfBook().equals(TypeOfBook.AUDIO_BOOK)) && book.getPublishingHouse().toLowerCase().startsWith(finalSearch)) {
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
}
