package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.basket.BasketInfo;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 25/1/22
 * Tuesday 17:01
 */
public interface BasketService {

    void addBookToBasket(Long clientId, Long bookId);

    void deleteBookFromBasket(Long clientId, Long bookId);

    BasketInfo getBasketByClientId(Long clientId);

    void cleanBasketByClientId(Long clientId);
}
