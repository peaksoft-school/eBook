package kg.ebooks.eBook.db.domain.model.books;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.*;

import javax.persistence.*;
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FileInfo fragment;

    private LocalTime duration;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FileInfo audioBook;
    // TODO: 8/1/22 add fragment
    // TODO: 8/1/22 add audio book

}
