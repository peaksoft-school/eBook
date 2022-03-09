package kg.ebooks.eBook.db.domain.dto.book;


import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetGenreLastPostBookDTO {
    private Long bookId;
    private String bookName;
    private GenreDTO genre;
    private BigDecimal price;
    private String description;
    private FileInfo image;
}
