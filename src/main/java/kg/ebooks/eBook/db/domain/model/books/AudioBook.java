package kg.ebooks.eBook.db.domain.model.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
public class AudioBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long audioBookId;

    // TODO: 8/1/22 add fragment
    // TODO: 8/1/22 add audio book
}
