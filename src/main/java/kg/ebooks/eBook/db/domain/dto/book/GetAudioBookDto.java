package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAudioBookDto {
    private Long bookId;
    private String bookName;
    private String author;
    private FileInfo image;
    private BigDecimal price;
    private LocalTime duration;


}
