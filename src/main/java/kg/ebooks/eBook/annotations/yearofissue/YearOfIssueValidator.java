package kg.ebooks.eBook.annotations.yearofissue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * @author Beksultan
 */
public class YearOfIssueValidator implements ConstraintValidator<YearOfIssue, Integer> {
    @Override
    public void initialize(YearOfIssue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        int currentYear = LocalDate.now().getYear();
        if (integer > currentYear || integer < currentYear - 50 ) {
            return false;
        }
        return true;
    }
}
