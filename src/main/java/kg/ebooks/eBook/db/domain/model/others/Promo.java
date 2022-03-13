package kg.ebooks.eBook.db.domain.model.others;

import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.*;

/**
 * created by Beksultan Mamatkadyr uulu
 */
@Entity
@Table(name = "promo_codes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promo_id;

    @NotBlank(message = "you have to define promo code name")
    private String promoName;

    @NotNull(message = "you have to define starting day of promo code")
    private LocalDate startingDay;

    @NotNull(message = "you have to define finishing day of prome code")
    private LocalDate finishingDay;

    @NotNull(message = "you have to define percent of promo code")
    private Byte percent;

    @ManyToOne(cascade = ALL)
    private Vendor promoCreator;

    public Book addPromoToBook(Book book) {
        if (book.getDiscount() + percent > 100) {
            book.setDiscount(100);
            return book;
        }
        book.addDiscount(percent);
        return book;
    }

    public boolean isValid() {
        LocalDate now = LocalDate.now();
        return now.isBefore(finishingDay) && now.isAfter(startingDay);
    }
}
