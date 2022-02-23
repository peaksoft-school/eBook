package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 15:43
 * hello world
 */
@Getter
@Setter
public class BookResponseDTOSort {
    private Long bookId;
    private FileInfo image;
    private String bookName;
    private String author;
    private BigDecimal price;
}
