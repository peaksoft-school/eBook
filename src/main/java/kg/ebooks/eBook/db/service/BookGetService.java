package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookInfo;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 6/2/22
 * Sunday 18:32
 * hello world
 */
public interface BookGetService {
    List<BookResponse> getAllAcceptedBooks();

    List<BookResponse> getAllBooksStorageRequests();

    BookInfo getBookById(Long bookId);
}
