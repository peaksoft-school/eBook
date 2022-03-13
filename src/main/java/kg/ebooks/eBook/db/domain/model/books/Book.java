package kg.ebooks.eBook.db.domain.model.books;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.basket.BookInfoBkt;
import kg.ebooks.eBook.db.domain.dto.book.Date;
import kg.ebooks.eBook.db.domain.dto.book.Time;
import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.*;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static kg.ebooks.eBook.db.domain.model.enums.TypeOfBook.*;

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
public class Book implements BookInfoBkt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @OneToMany(cascade = {MERGE, REFRESH})
    private Set<FileInfo> images;

    private String bookName;

    private String author;

    @ManyToOne(cascade = {MERGE, REMOVE})
    private Genre genre;

    private Language language;

    private int yearOfIssue;

    private BigDecimal price;

    private Boolean bestSeller;

    private int discount;

    @NotNull(message = "you have to define type of book")
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

    public void setImages(Set<FileInfo> images) {
        System.out.println("inside book");
        images.forEach(System.out::println);
        this.images = images;
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
        try {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(genre, GenreDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isNew() {
        LocalDate now = LocalDate.now().minusDays(30);
        return now.isBefore(storageDate);
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

    public Genre getOriginalGenre() {
        return this.genre;
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

    public void setImage(FileInfo newImage) {
        this.images.add(newImage);
    }

    public void removeGenre() {
        this.genre = null;
    }

    public int getPageSize() {
        return typeOfBook.equals(PAPER_BOOK) ?
                paperBook.getPageSize() :
                typeOfBook.equals(ELECTRONIC_BOOK) ? electronicBook.getPageSize() : 0;
    }

    public int getQuantityOfBooks() {
        return typeOfBook.equals(PAPER_BOOK) ? paperBook.getQuantityOfBooks() : 0;
    }

    public Time getDuration() {
        AudioBook audioBook = this.getAudioBook();
        if (audioBook == null) return null;

        Time time = new Time();
        time.setHour((byte) audioBook.getDuration().getHour());
        time.setMinute((byte) audioBook.getDuration().getMinute());
        time.setSecond((byte) audioBook.getDuration().getSecond());
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return yearOfIssue == book.yearOfIssue && discount == book.discount && likes == book.likes && inBasket == book.inBasket && Objects.equals(bookId, book.bookId) && Objects.equals(images, book.images) && Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre) && language == book.language && Objects.equals(price, book.price) && Objects.equals(bestSeller, book.bestSeller) && typeOfBook == book.typeOfBook && Objects.equals(description, book.description) && Objects.equals(storageDate, book.storageDate) && Objects.equals(audioBook, book.audioBook) && Objects.equals(electronicBook, book.electronicBook) && Objects.equals(paperBook, book.paperBook) && requestStatus == book.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, images, bookName, author, genre, language, yearOfIssue, price, bestSeller, discount, typeOfBook, description, storageDate, audioBook, electronicBook, paperBook, requestStatus, likes, inBasket);
    }
}
