package kg.ebooks.eBook.db.domain.model.books;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.basket.BookInfoBkt;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.domain.model.others.Image;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.others.Promo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
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
public class Book implements BookInfoBkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @OneToMany(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private List<FileInfo> images;

    @NotBlank(message = "Book name is required!")
    private String bookName;

    @NotBlank(message = "Author full name is required!")
    private String author;

    @ManyToOne(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    @NotNull(message = "book should have genre!")
    private Genre genre;

    @NotBlank(message = "Publishing house is required!")
    private String publishingHouse;

    @NotNull(message = "you missed the language")
    private Language language;

    @NotNull(message = "you missed date of issue")
    private LocalDate dateOfIssue;

    @NotNull(message = "you have to define the page size")
    private int pageSize;

    @NotNull(message = "you have to define prise of book")
    private BigDecimal price;

    @NotNull(message = "you have to define is Book bestSeller or not")
    private Boolean bestSeller;

    @Min(value = 1, message = "you can give min 1 percent to make a discount")
    @Max(value = 100, message = "you can give max 100 percent to make a discount")
    private byte discount;

    @NotNull(message = "you have to define type of book")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private TypeOfBook typeOfBook;

    @NotBlank(message = "you have to define description to this book")
    @Column(length = 10000)
    private String description;

    private LocalDate storageDate;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private AudioBook audioBook;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private ElectronicBook electronicBook;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private PaperBook paperBook;


    @Override
    public FileInfo getImage() {
        return images.get(0);
    }

    @Override
    public BigDecimal getDiscountPrice() {
        BigDecimal bigDecimal = price.multiply(BigDecimal.valueOf(discount))
                .divide(BigDecimal.valueOf(100));
        return price.subtract(bigDecimal);
    }

    @Override
    public BigDecimal getNetPrice() {
        return price;
    }
}
