package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.basket.BasketInfo;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 25/1/22
 * Tuesday 17:01
 */
public interface BasketService {

    void addBookToBasket(String clientEmail, Long bookId);

    void deleteBookFromBasket(String clientEmail, Long bookId);

    BasketInfo getBasketByClientId(String clientEmail);

    void cleanBasketByClientId(String clientEmail);
}
