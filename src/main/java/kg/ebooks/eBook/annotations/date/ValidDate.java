package kg.ebooks.eBook.annotations.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 10:52
 * hello world
 */
@Documented
@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidDate {
    String message() default "Invalid date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
