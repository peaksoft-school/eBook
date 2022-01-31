package kg.ebooks.eBook.db.domain.dto.basket;

import kg.ebooks.eBook.db.domain.dto.basket.impl.BookInfoBktImpl;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 17:49
 */

public interface BasketInfo {
    Long getBasketId();

    List<BookInfoBktImpl> getBooksBkt();

    int getQuantityOfBooks();
}
