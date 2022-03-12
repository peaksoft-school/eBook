package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.SearchDto;

import java.util.List;
import java.util.Set;

public interface SearchService {

    List<SearchDto> findAll(String search) ;

    Set<BookResponse> findByAuthorName(String authorName);

    Set<BookResponse> findByPublishingHouse(String publisher);
}
