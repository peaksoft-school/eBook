package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.db.domain.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    private Long id;
    private String search;
    private Type bookOrGenre;
}
