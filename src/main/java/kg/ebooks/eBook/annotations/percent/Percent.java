package kg.ebooks.eBook.annotations.percent;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 11:00
 * hello world
 */

@Documented
@Retention(RUNTIME)
@Target({METHOD, FIELD})
@Constraint(validatedBy = PercentValidator.class)
public @interface Percent {
    String message() default "Percent should be [0-100] %";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
