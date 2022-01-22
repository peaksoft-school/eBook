package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.main_card.BookInfo1;
import kg.ebooks.eBook.db.domain.dto.main_card.BookInfoForAudio;
import kg.ebooks.eBook.db.domain.dto.book.BookInfo;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 21/1/22
 * Friday 11:55
 */
public interface BookService {

    List<BookInfo1> getAllBooks();

    List<BookInfoForAudio> getAllAudioBooks();

    List<BookInfo1> getAllBestsellerBooks();

    List<BookInfo1> getLatestBooks();

    List<BookInfo1> getAllElectronicBooks();

    BookInfo getBookById(Long bookId);
}
