package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 2/2/22
 * Wednesday 09:54
 * hello world
 */
@NoArgsConstructor
@Getter @Setter
public class BookSave<BOOK>{

    private List<FileInfo> images;

    @NotNull(message = "type of book must not be null")
    private TypeOfBook typeOfBook;

    @NotBlank(message = "book name must not be null")
    private String bookName;

    @NotBlank(message = "author must not be null")
    private String author;

    @NotNull(message = "genreId must not be null")
    private Long genreId;

    @Column(length = 1234)
    private String description;

    @NotNull(message = "Language must not be null")
    private Language language;

    @NotNull(message = "data of issue must not be null")
    private LocalDate dataOfIssue;

    private Boolean bestSeller;

    @NotNull(message = "price must not be null")
    private BigDecimal price;

    private byte discount;

    private BOOK book;

}
