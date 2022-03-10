package kg.ebooks.eBook.db.domain.dto.sort;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Beksultan
 */
@XStreamAlias(value = "sortBy")
@Getter @Setter
public class SortBy {
    private Property propertyName;
}
