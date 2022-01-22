package kg.ebooks.eBook.db.domain.dto.book.impl;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.book.BookInfo;
import kg.ebooks.eBook.db.domain.model.enums.Language;
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
public class AudioBookDTO implements BookInfo {

    private Long bookId;
    private String bookName;
    private List<FileInfo> Images;
    private String author;
    private BigDecimal price;
    private Genre genre;
    private Language language;
    private String publishingHouse;
    private LocalDate dateOfIssue;
    private int pageSize;
    private String description;
    private FileInfo audioFragment;
    private boolean isNew;
}
