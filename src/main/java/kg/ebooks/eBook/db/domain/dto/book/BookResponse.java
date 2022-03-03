package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * created by Beksultan Mamatkadyr uulu
 * 27/1/22
 * Thursday 17:43
 * hello world
 */
@NoArgsConstructor
@Getter @Setter
public class BookResponse {
    private Long bookId;
    private FileInfo image;
    private String bookName;
    private String author;
    private BigDecimal netPrice;
    private int discount;
    private BigDecimal discountedPrice;
    private int likes;
    private int inBasket;
}
