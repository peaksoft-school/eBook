package kg.ebooks.eBook.db.domain.model.users;

import kg.ebooks.eBook.db.domain.model.others.Basket;
import kg.ebooks.eBook.db.domain.model.others.SelectedBooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

/**

 */
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;

    @NotBlank(message = "you have to define a first name for the CLient")
    private String name;

    @Email(message = "you must define '@' in email address")
    private String email;

    private boolean subscriptionToNewsLetter;

    private LocalDate dateOfRegistration;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private Basket basket = new Basket();

    @OneToOne(fetch = LAZY, cascade = ALL)
    private SelectedBooks selectedBooks = new SelectedBooks();

    @OneToOne(fetch = EAGER, cascade = ALL)
    private AuthenticationInfo authenticationInfo;

    public Client(Long clientId, String name, String email) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
    }
}
