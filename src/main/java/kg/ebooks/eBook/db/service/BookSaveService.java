package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.enums.Response;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;

import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 1/2/22
 * Tuesday 14:34
 * hello world
 */
public interface BookSaveService {

    Set<BookResponse> findALLBooks();

    BookResponse saveBook(AuthenticationInfo authority, TypeOfBook typeOfBook, BookSave<?> book);

    BookResponse updateBook(Long bookId, TypeOfBook paperBook, BookSave<?> bookSave);

    Response deleteBook(String email, Authority authority, Long bookId);
}
