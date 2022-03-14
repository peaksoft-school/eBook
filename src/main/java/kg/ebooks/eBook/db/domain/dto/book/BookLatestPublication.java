package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import lombok.*;

/**
 * @author Beksultan
 */
@Builder
@AllArgsConstructor
@Getter @Setter
public class BookLatestPublication implements BookMainPage{
    private GenreDTO genre;
    private BookMainPageBestSellerAndEBookAndLatestDto bookResponse;
}
