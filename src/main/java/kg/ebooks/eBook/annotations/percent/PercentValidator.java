package kg.ebooks.eBook.annotations.percent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 11:01
 * hello world
 */
public class PercentValidator implements ConstraintValidator<Percent, Integer> {

    @Override
    public boolean isValid(Integer integer,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (integer >= 0 && integer <= 100) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(Percent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
