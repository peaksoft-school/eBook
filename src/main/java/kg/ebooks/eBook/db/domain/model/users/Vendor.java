package kg.ebooks.eBook.db.domain.model.users;

import kg.ebooks.eBook.db.domain.model.others.Address;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.others.Promo;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 8/1/22
 * Saturday 23:14
 */
@Entity
@Table(name = "vendors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vendorId;

    @NotBlank(message = "you have to define a first name for the vendor")
    private String firstName;

    @NotBlank(message = "you have to define a last name for the vendor")
    private String lastName;

    @Email(message = "you must define '@' in email address")
    private String email;

    @OneToOne(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private Address address;

    @NotBlank(message = "you have to define a phone number for the vendor")
    private String phoneNumber;

    private String nameOfBranch;

    @OneToMany(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private List<Book> booksToSale = new ArrayList();

    @OneToOne(fetch = LAZY, cascade = ALL)
    private AuthenticationInfo authenticationInfo;

    @OneToMany(fetch = LAZY, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private List<Promo> promoCodes = new ArrayList();

    public void setBook(Book save) {
        this.booksToSale.add(save);
    }
}
