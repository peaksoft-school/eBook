package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
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

   }
