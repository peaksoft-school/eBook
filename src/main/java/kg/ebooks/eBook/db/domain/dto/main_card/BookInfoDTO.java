package kg.ebooks.eBook.db.domain.dto.main_card;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 21/1/22
 * Friday 12:54
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class BookInfoDTO implements BookInfo1 {

    private Long bookId;
    private List<FileInfo> images;
    private String bookName;
    private BigDecimal price;
    private String description;
    private boolean isNew;

}
