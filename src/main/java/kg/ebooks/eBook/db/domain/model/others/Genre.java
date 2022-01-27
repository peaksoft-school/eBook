package kg.ebooks.eBook.db.domain.model.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 8/1/22
 * Saturday 20:27
 */
@Entity
@Table(name = "genres")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String genreName;

    private Integer quantityOfBooks;

}
