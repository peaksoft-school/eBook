package kg.ebooks.eBook.db.domain.model.books;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.main_card.BookInfo1;
import kg.ebooks.eBook.db.domain.dto.main_card.BookInfoForAudio;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
@ToString
public class Book implements BookInfo1, BookInfoForAudio {
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

    private BigDecimal discount;

    @NotNull(message = "you have to define type of book")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private TypeOfBook typeOfBook;

    @NotBlank(message = "you have to define description to this book")
    @Column(length = 10000)
    private String description;

    private LocalDate storageDate;

    @OneToOne(fetch = EAGER, cascade = ALL)
    private AudioBook audioBook;

    @OneToOne(fetch = EAGER, cascade = ALL)
    private ElectronicBook electronicBook;

    @OneToOne(fetch = EAGER, cascade = ALL)
    private PaperBook paperBook;

    public FileInfo getAudioFragment() {
        return audioBook.getFragment();
    }

    public String getFragment() {
        return switch (typeOfBook) {
            case ELECTRONIC_BOOK -> electronicBook.getFragment();
            case PAPER_BOOK -> paperBook.getFragment();
            default -> null;
        };
    }

    @Override
    public boolean isNew() {
        LocalDate now = LocalDate.now().minusDays(30);
        return now.isBefore(storageDate);
    }

    @Override
    public LocalTime getDuration() {
        if (audioBook == null) {
            return null;
        }
        return audioBook.getDuration();
    }
}
