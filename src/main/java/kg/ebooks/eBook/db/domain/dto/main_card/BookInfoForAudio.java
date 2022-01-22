package kg.ebooks.eBook.db.domain.dto.main_card;

import kg.ebooks.eBook.aws.model.FileInfo;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 22/1/22
 * Saturday 15:02
 */
public interface BookInfoForAudio {

    Long getBookId();

    List<FileInfo> getImages();

    String getBookName();

    String getAuthor();

    BigDecimal getPrice();

    String getDescription();

    LocalTime getDuration();

    boolean isNew();
}
