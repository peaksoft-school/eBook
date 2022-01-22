package kg.ebooks.eBook.db.domain.model.books;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 8/1/22
 * Saturday 20:34
 */
@Entity
@Table(name = "electronic_books")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ElectronicBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10000)
    private String fragment;

    @OneToOne(cascade = CascadeType.ALL)
    private FileInfo electronicBook;
    // TODO: 8/1/22 add fragment to electronic book
    // TODO: 8/1/22 add electronic book
}
