package kg.ebooks.eBook.db.domain.model.books;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 8/1/22
 * Saturday 20:32
 */
@Entity
@Table(name = "audio_books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AudioBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private FileInfo fragment;

    @DateTimeFormat(pattern = "hh-MM-ss")
    private LocalTime duration;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private FileInfo audioBook;

}
