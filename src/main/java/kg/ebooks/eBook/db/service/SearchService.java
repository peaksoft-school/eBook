package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.SearchDto;

import java.util.Set;

public interface SearchService {

    Set<SearchDto> findAll(String search) ;

    Set<BookResponse> findByAuthorName(String authorName);

    Set<BookResponse> findByPublishingHouse(String publisher);
}
