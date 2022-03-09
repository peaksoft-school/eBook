package kg.ebooks.eBook.db.domain.dto.genre;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 30/1/22
 * Sunday 18:09
 * hello world
 */

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreGetDTO {

    Long id;

    String genreName;

    int quantityOfBooks;

    Set<BookResponse> books;
}
