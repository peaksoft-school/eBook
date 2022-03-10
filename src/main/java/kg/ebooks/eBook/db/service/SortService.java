package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.BookResponseDTOSort;
import kg.ebooks.eBook.db.domain.dto.sort.SortRequest;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 16:08
 * hello world
 */
public interface SortService {
    List<BookResponseDTOSort> sort(SortRequest sortRequest);

    List<BookResponse> findAllByType(String type);

    List<BookResponseDTOSort> sort(String filterBy, String sortBy);
}
