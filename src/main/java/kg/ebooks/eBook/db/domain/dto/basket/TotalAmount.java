package kg.ebooks.eBook.db.domain.dto.basket;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Beksultan
 */
@Data
@Builder
public class TotalAmount {
    private Long quantityOfBooks;
    private BigDecimal discount;
    private BigDecimal amount;
    private BigDecimal total;
}
