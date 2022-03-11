package kg.ebooks.eBook.db.domain.dto.admin;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.book.Date;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 14:25
 * hello world
 */
@Getter
@Setter
public class BookResponseDTOFromAdmin {

    private Long bookId;

    private FileInfo image;

    private String bookName;

    private Date storageDate;

    private BigDecimal price;

}
