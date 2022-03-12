package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.annotations.percent.Percent;
import kg.ebooks.eBook.annotations.yearofissue.YearOfIssue;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 2/2/22
 * Wednesday 09:54
 * hello world
 */
@NoArgsConstructor
@Getter @Setter
public class BookSave<BOOKTYPE extends BookRequest> {

    private Set<Long> images;

    @NotBlank(message = "Book name is required!")
    private String bookName;

    @NotBlank(message = "Author full name is required!")
    private String author;

    @NotNull(message = "book should have genre!")
    private Long genreId;

    @Column(length = 1234)
    @NotBlank(message = "you have to define description to this book")
    private String description;

    @NotNull(message = "you missed the language")
    private Language language;

    @NotNull(message = "you missed date of issue")
    @YearOfIssue
    private int yearOfIssue;

    @NotNull(message = "you have to define is Book bestSeller or not")
    private Boolean bestSeller;

    @NotNull(message = "you have to define prise of book")
    private BigDecimal price;

    @Percent
    private int discount;

    private BOOKTYPE book;

}
