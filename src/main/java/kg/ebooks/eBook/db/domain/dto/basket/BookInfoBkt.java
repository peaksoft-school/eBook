package kg.ebooks.eBook.db.domain.dto.basket;

import kg.ebooks.eBook.aws.model.FileInfo;

import java.math.BigDecimal;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 17:32
 */
public interface BookInfoBkt {
    //    #BOOKINFOBKT

    Long getBookId();

    //    ⌘ one main photo
    FileInfo getImage();

    //    ⌘ book name
    String getBookName();

    //    ⌘ author of book
    String getAuthor();
//
////    ⌘ promo
//    Promo getPromo();

    //    ⌘ discount
    byte getDiscount();

    //    ⌘ discount price
    BigDecimal getDiscountedPrice();

    //    ⌘ net prices
    BigDecimal getNetPrice();
}
