package kg.ebooks.eBook.db.domain.model.others;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.genre.GenreGetDTO;
import kg.ebooks.eBook.db.domain.model.books.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Genre implements GenreGetDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String genreName;

    @OneToMany(fetch = EAGER, cascade = {REFRESH, PERSIST, DETACH, MERGE})
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    private int quantityOfBooks;

    @Override
    public Set<BookResponse> getAllBooks() {
        ModelMapper modelMapper = new ModelMapper();
        return books.stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toSet());
    }

    public void setBook(Book book) {
        books.add(book);
    }

    public void count() {
        quantityOfBooks++;
    }
}
