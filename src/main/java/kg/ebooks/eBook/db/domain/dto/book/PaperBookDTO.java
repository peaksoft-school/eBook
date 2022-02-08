package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.dto.genre.GenreGetDTO;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 01:44
 */
@Getter
@Setter
public class PaperBookDTO implements BookInfo {

    private Long bookId;
    private TypeOfBook typeOfBook;
    private String bookName;
    private List<FileInfo> Images;
    private String author;
    private BigDecimal price;
    private GenreDTO genre;
    private Language language;
    private String publishingHouse;
    private int yearOfIssue;
    private int pageSize;
    private String description;
    private String fragment;
    private boolean isNew;
}
