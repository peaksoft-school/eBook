package kg.ebooks.eBook.annotations.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 10/2/22
 * Thursday 12:01
 * hello world
 */
@Documented
@Retention(RUNTIME)
@Target(value = {METHOD, FIELD})
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
