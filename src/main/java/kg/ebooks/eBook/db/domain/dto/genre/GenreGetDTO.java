package kg.ebooks.eBook.db.domain.dto.genre;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;

import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 30/1/22
 * Sunday 18:09
 * hello world
 */
public interface GenreGetDTO {

    Long getId();

    String getGenreName();

    int getQuantityOfBooks();

    Set<BookResponse> getAllBooks();
}
