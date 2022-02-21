package kg.ebooks.eBook.db.domain.dto.basket;

import kg.ebooks.eBook.db.domain.dto.basket.impl.BookInfoBktImpl;
import kg.ebooks.eBook.db.domain.model.books.Book;
import lombok.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 18/2/22
 * Friday 09:46
 * hello world
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BasketBook {
    private BookInfoBktImpl book;
    private Long quantityOfBooks;

    public void increment() {
        this.quantityOfBooks++;
    }
}
