package kg.ebooks.eBook.db.domain.model.others;

import kg.ebooks.eBook.db.domain.model.books.Book;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 11:21
 */
public interface BookCase {
    void setBook(Book book);

    void deleteBook(Book book);

    void clear();
}
