package kg.ebooks.eBook.db.domain.model.books;

import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.domain.model.others.Image;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 8/1/22
 * Saturday 20:38
 */
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @OneToMany(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private List<Image> images;

    @NotBlank(message = "Book name is required!")
    private String bookName;

    @NotBlank(message = "Author full name is required!")
    private String authorFullName;

    @ManyToOne(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    @NotNull(message = "book should have genre!")
    private Genre genre;

    @NotBlank(message = "Publishing house is required!")
    private String publishingHouseName;

    @NotNull(message = "you missed the language")
    private Language language;

    @NotNull(message = "you missed date of issue")
    private LocalDate dateOfIssue;

    @NotNull(message = "you have to define the page size")
    private int pageSize;

    @NotNull(message = "you have to define prise of book")
    private BigDecimal prise;

    @NotNull(message = "you have to define is Book bestSeller or not")
    private Boolean bestSeller;

    private BigDecimal discount;

    @NotNull(message = "you have to define type of book")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private TypeOfBook typeOfBook;

    @NotBlank(message = "you have to define description to this book")
    private String description;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private AudioBook audioBook;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private ElectronicBook electronicBook;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private PaperBook paperBook;

}
