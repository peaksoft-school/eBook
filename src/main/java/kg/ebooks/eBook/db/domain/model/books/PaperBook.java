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
 * Saturday 20:36
 */
@Entity
@Table(name = "paper_books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaperBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10000)
    private String fragment;

    // #I don't know how to do paper book?
}
