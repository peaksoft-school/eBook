package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.promo.PromoCreate;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 02:35
 * hello world
 */
public interface PromoService {
    String createPromo(String email, PromoCreate promo);

    List<BookResponse> findPromo(String promo);
}
