package kg.ebooks.eBook.db.domain.model.books;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.basket.BookInfoBkt;
import kg.ebooks.eBook.db.domain.dto.book.Date;
import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
@EqualsAndHashCode
public class Book implements BookInfoBkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @OneToMany(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private Set<FileInfo> images;

    private String bookName;

    private String author;

    @ManyToOne(fetch = EAGER, cascade = {DETACH, REFRESH, PERSIST})
    private Genre genre;

    private Language language;

    private LocalDate dateOfIssue;

    private BigDecimal price;

    private Boolean bestSeller;

    private int discount;

    @NotNull(message = "you have to define type of book")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private TypeOfBook typeOfBook;

    @Column(length = 10000)
    private String description;

    private LocalDate storageDate;

    @OneToOne(fetch = EAGER, cascade = ALL)
    private AudioBook audioBook;

    @OneToOne(fetch = EAGER, cascade = ALL)
    private ElectronicBook electronicBook;

    @OneToOne(fetch = EAGER, cascade = ALL)
    private PaperBook paperBook;

    private RequestStatus requestStatus;

    private int likes;

    private int inBasket;

    public void like() {
        this.likes++;
    }

    public void disLike() {
        this.likes--;
    }

    public void incrementInBasket() {
        this.inBasket++;
    }

    public void decrementInBasket() {
        this.inBasket--;
    }

    public LocalDate getOriginalStorageDate() {
        return this.storageDate;
    }
    public Date getStorageDate() {
        Date date = new Date();
        date.setDay(storageDate.getDayOfMonth());
        date.setMonth(storageDate.getMonth());
        date.setYear(storageDate.getYear());
        return date;
    }

    @Override
    public FileInfo getImage() {
        return images.stream().findAny()
                .orElseThrow(() -> new DoesNotExistsException(
                        "image in book does not exists"
                ));
    }

    @Override
    public BigDecimal getDiscountedPrice() {
        BigDecimal bigDecimal = price.multiply(BigDecimal.valueOf(discount))
                .divide(BigDecimal.valueOf(100));
        return price.subtract(bigDecimal);
    }

    @Override
    public BigDecimal getNetPrice() {
        return price;
    }

    public GenreDTO getGenre() {
        ModelMapper modelMapper = new ModelMapper();
        System.out.println(genre);
        System.out.println(bookName);
        return modelMapper.map(genre, GenreDTO.class);
    }

    public boolean isNew() {
        LocalDate now = LocalDate.now().minusDays(30);
        return now.isBefore(storageDate);
    }

    public int getYearOfIssue() {
        return dateOfIssue.getYear();
    }

    public String getPublishingHouse() {
        if (paperBook != null) {
            return paperBook.getPublishingHouse();
        }

        if (electronicBook != null) {
            return electronicBook.getPublishingHouse();
        }
        return "hello";
    }


    public String getFragment() {
        if (paperBook != null) {
            return paperBook.getFragment();
        }

        if (electronicBook != null) {
            return electronicBook.getFragment();
        }
        return "no fragment";
    }
}
