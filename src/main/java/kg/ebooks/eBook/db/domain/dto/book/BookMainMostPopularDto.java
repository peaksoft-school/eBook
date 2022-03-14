package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Beksultan
 */
@Getter @Setter
public class BookMainMostPopularDto implements BookMainPage{
    private Long bookId;
    private FileInfo image;
    private String bookName;
    private String author;
    private BigDecimal price;
}
