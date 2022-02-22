package kg.ebooks.eBook.db.domain.dto.sort;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 16:10
 * hello world
 */
@Getter
@Setter
public class SortRequest {

    @Nullable
    private List<Long> genres;

    @JsonProperty("type")
    @Nullable
    private TypeOfBook typeOfBook;

    @Nullable
    private Price price;

    @Nullable
    private List<Language> languages;
}
