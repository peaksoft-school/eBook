package kg.ebooks.eBook.db.domain.dto.promo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 02:30
 * hello world
 */
@Getter
@Setter
public class PromoCreate {
    @NotBlank(message = "you have to define promo code name")
    private String promoName;
    // TODO: 19/2/22 create valid date validation annonation
    private LocalDate startingDay;
    private LocalDate finishingDay;
    // TODO: 19/2/22 valid percent
    private int percent;
}
