package kg.ebooks.eBook.db.domain.dto.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * created by Beksultan Mamatkadyr uulu
 * 4/2/22
 * Friday 15:11
 * hello world
 */
@Getter
@Setter
public class PaperBookRequest implements BookRequest {
    @NotBlank(message = "you have to write fragment for this book")
    private String fragment;
    @Min(value = 1, message = "quantity of books must be minimum 1")
    private int quantityOfBooks;
    @Min(value = 5, message = "book should contain min 5 pages")
    private int pageSize;
    @NotBlank(message = "you have to write publishing house")
    private String publishingHouse;
}
