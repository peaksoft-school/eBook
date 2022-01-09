package kg.ebooks.eBook.db.domain.model;

import kg.ebooks.eBook.db.domain.model.books.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 9/1/22
 * Sunday 10:32
 */
@Entity
@Table(name = "baskets")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    @ManyToMany(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private List<Book> books;

    private int quantityOfBooks;
}
