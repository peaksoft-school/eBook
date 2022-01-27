package kg.ebooks.eBook.db.domain.dto.basket.impl;

import kg.ebooks.eBook.db.domain.dto.basket.BasketInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 17:55
 */
@Getter
@Setter
public class BasketInfoImpl implements BasketInfo {
    private Long basketId;
    private int quantityOfBooks;
    private List<BookInfoBktImpl> booksBkt;
}
