package kg.ebooks.eBook.db.domain.dto.sort;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import org.apache.commons.codec.language.bm.Lang;

import java.util.Set;

/**
 * @author Beksultan
 */
@XStreamAlias(value = "filterBy")
public class SortRequestJSON {
    private Set<Long> genres;
    private Set<Language> languages;
}
