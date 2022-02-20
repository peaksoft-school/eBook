package kg.ebooks.eBook.db.domain.dto.promo;

import kg.ebooks.eBook.annotations.date.ValidDate;
import kg.ebooks.eBook.annotations.percent.Percent;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
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
    @ValidDate(message = "you wrote invalid starting day")
    private LocalDate startingDay;

    @ValidDate(message = "you wrote invalid finishing day")
    private LocalDate finishingDay;

    // TODO: 19/2/22 valid percent
    @Percent
    private int percent;
}
