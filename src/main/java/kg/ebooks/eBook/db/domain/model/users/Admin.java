package kg.ebooks.eBook.db.domain.model.users;

import kg.ebooks.eBook.db.domain.model.books.Book;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 9/1/22
 * Sunday 10:26
 */
@Entity
@Table(name = "admins")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;

    @NotBlank(message = "you have to define a first name for the admin")
    private String firstName;

    @NotBlank(message = "you have to define a last name for the admin")
    private String lastName;

    @Email(message = "you must define '@' in email address")
    private String email;

    @OneToMany(cascade = MERGE)
    private List<Book> books = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    private AuthenticationInfo authenticationInfo;

    public void setBook(Book book) {
        this.books.add(book);
    }
}
