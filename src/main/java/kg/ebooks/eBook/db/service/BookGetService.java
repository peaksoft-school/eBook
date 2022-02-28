package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.admin.BookResponseDTOFromAdmin;
import kg.ebooks.eBook.db.domain.dto.book.BookInfo;
import kg.ebooks.eBook.db.domain.dto.book.BookMainPage;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.GetAudioBookDto;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 6/2/22
 * Sunday 18:32
 * hello world
 */
public interface BookGetService {
    List<BookResponseDTOFromAdmin> getAllAcceptedBooks();

    List<BookResponseDTOFromAdmin> getAllBooksStorageRequests();

    BookInfo getBookById(Long bookId);

    List<BookMainPage> getThreeBooks();
    List<GetAudioBookDto> getAudioBook();



}
