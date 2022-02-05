package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.model.books.Book;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * created by Beksultan Mamatkadyr uulu
 * 4/2/22
 * Friday 15:11
 * hello world
 */
@Getter @Setter
public class ElectronicBookRequest implements BookRequest {
    @Column(length = 10000)
    @NotBlank(message = "you have to define ")
    private String fragment;

    @NotNull(message = "you have to define the page size")
    private int pageSize;

    @NotBlank(message = "Publishing house is required!")
    private String publishingHouse;

    @NotNull(message = "you have to define electronic book [.pdf]")
    private Long electronicBookId;
}
