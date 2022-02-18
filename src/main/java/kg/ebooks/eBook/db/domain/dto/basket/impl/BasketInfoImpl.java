package kg.ebooks.eBook.db.domain.dto.basket.impl;

import kg.ebooks.eBook.db.domain.dto.basket.BasketBook;
import kg.ebooks.eBook.db.domain.dto.basket.BasketInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 17:55
 */
@Getter
@Setter
@EqualsAndHashCode
public class BasketInfoImpl implements BasketInfo {
    private Long basketId;
    private int quantityOfBooks;
    private Set<BasketBook> booksBkt;
}
