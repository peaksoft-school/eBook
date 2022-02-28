package kg.ebooks.eBook.db.domain.dto.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.model.books.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalTime;

/**
 * created by Beksultan Mamatkadyr uulu
 * 2/2/22
 * Wednesday 19:15
 * hello world
 */
@Getter @Setter
public class AudioBookRequest implements BookRequest {
    private Long fragmentId;
    private Time duration;
    private Long audioBookId;
}
