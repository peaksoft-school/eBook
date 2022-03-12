package kg.ebooks.eBook.annotations.yearofissue;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Beksultan
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD, FIELD})
@Constraint(validatedBy = YearOfIssueValidator.class)
public @interface YearOfIssue {
    String message() default "Percent should be [0-100] %";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
