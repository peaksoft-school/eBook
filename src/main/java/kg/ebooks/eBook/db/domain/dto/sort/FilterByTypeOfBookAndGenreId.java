package kg.ebooks.eBook.db.domain.dto.sort;

import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import lombok.Data;

/**
 * @author Beksultan
 */
@Data
public class FilterByTypeOfBookAndGenreId {
    private Long genreId;
    private TypeOfBook typeOfBook;
}
