package kg.ebooks.eBook.db.domain.dto.main_card;

import kg.ebooks.eBook.aws.model.FileInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 21/1/22
 * Friday 16:18
 */
public interface BookInfo1 {

    Long getBookId();

    List<FileInfo> getImages();

    String getBookName();

    BigDecimal getPrice();

    String getDescription();

    boolean isNew();

}
