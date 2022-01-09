package kg.ebooks.eBook.db.domain.model.users;

import kg.ebooks.eBook.db.domain.model.Basket;
import kg.ebooks.eBook.db.domain.model.SelectedBooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 9/1/22
 * Sunday 10:29
 */
@Entity
@Table(name = "clients")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @NotBlank(message = "you have to define a first name for the CLient")
    private String name;

    @Email(message = "you must define '@' in email address")
    private String email;

    private boolean subscriptionToNewsLetter;

    @OneToOne(fetch = LAZY, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private Basket basket;

    @OneToOne(fetch = LAZY, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private SelectedBooks selectedBooks;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private AuthenticationInfo authenticationInfo;
}
