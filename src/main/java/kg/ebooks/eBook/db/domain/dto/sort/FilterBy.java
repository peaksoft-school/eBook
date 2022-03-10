package kg.ebooks.eBook.db.domain.dto.sort;

import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.util.Set;

/**
 * @author Beksultan
 */
@Getter
@Setter
@ToString
public class FilterBy {
    @Nullable
    private Set<Long> genres;

    @Nullable
    private TypeOfBook typeOfBook;

    @Nullable
    private Price price;

    @Nullable
    private Set<Language> languages;
}
