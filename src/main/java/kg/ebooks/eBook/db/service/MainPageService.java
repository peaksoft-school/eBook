package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookMainPage;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.main.MainPageRequest;

import java.util.List;

/**
 * @author Beksultan
 */
public interface MainPageService {

    List<BookMainPage> findByRequest(MainPageRequest request);
}
