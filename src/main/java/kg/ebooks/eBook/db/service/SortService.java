package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.BookResponseDTOSort;

import java.util.List;
import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 16:08
 * hello world
 */
public interface SortService {

    List<BookResponse> findAllByType(String type);

    Set<BookResponseDTOSort> sort(String filterBy);
}
