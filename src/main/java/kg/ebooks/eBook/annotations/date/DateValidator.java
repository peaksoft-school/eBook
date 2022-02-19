package kg.ebooks.eBook.annotations.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 10:54
 * hello world
 */
public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate now = LocalDate.now();
        LocalDate expiredAt = LocalDate.now().plusYears(1L);

        if (date.isBefore(now) && date.isBefore(expiredAt)) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
