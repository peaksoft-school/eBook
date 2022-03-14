package kg.ebooks.eBook.db.domain.dto.basket;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author Beksultan
 */
@Getter @Setter
@ToString
public class TotalAmount {
    private Long quantityOfBooks = 0L;
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal discount = new BigDecimal(0);
    private BigDecimal total = new BigDecimal(0);
    private boolean hasAPromo = false;

    public void setTotalAmount(TotalAmount totalAmount) {
        this.quantityOfBooks++;
        discount = discount.add(totalAmount.getDiscount());
        amount = this.amount.add(totalAmount.getAmount());
        total = this.total.add(totalAmount.getTotal());
    }
}
