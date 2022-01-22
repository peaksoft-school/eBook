package kg.ebooks.eBook.db.domain.dto.main_card;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 22/1/22
 * Saturday 15:16
 */
@NoArgsConstructor
@Getter @Setter
public class AudioBookInfoDTO implements BookInfoForAudio {

    private Long bookId;

    private List<FileInfo> images;

    private String bookName;

    private String author;

    private BigDecimal price;

    private String description;

    private LocalTime duration;

    private boolean isNew;

}
