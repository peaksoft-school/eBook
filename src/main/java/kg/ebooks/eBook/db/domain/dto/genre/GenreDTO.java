package kg.ebooks.eBook.db.domain.dto.genre;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * created by Beksultan Mamatkadyr uulu
 * 27/1/22
 * Thursday 14:02
 * hello world
 */
@NoArgsConstructor
@Getter @Setter
public class GenreDTO {
    private Long id;
    private String genreName;
    private int quantityOfBooks;
}
