package kg.ebooks.eBook.db.domain.model.others;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.genre.GenreGetDTO;
import kg.ebooks.eBook.db.domain.model.books.Book;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

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
@ToString
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String genreName;

    @OneToMany(fetch = EAGER, cascade = MERGE, orphanRemoval = true)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    private int quantityOfBooks = 0;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Set<Book> getOriginalBooks() {
        return this.books;
    }

    public void setBook(Book book) {
        books.add(book);
    }

    public void count() {
        quantityOfBooks++;
    }

    public void remove(Book book) {
        books.remove(book);
    }
}
