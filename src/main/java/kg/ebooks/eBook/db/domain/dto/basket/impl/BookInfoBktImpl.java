package kg.ebooks.eBook.db.domain.dto.basket.impl;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.basket.BookInfoBkt;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 17:49
 */

@Getter
@Setter
public class BookInfoBktImpl implements BookInfoBkt {
    //    #BOOKINFOBKT
    private Long bookId;
    //    ⌘ one main photo
    private FileInfo image;
    //    ⌘ book name
    private String bookName;
    //    ⌘ author of book
    private String author;
    //    //    ⌘ promo
//    private Promo promo;
    //    ⌘ discount
    private byte discount;
    //    ⌘ discount price
    private BigDecimal discountedPrice;
    //    ⌘ net prices
    private BigDecimal netPrice;

}
