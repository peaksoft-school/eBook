package kg.ebooks.eBook.db.domain.dto.sort;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 16:13
 * hello world
 */
@Getter
@Setter
public class Price {
    private Integer from;
    private Integer before;

    public boolean valid(BigDecimal bigDecimal) {
        int i = bigDecimal.intValue();
        return i >= from && i <= before;
    }
}
