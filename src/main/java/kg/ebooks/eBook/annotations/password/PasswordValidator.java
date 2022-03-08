package kg.ebooks.eBook.annotations.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * created by Beksultan Mamatkadyr uulu
 * 10/2/22
 * Thursday 12:03
 * hello world
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (password.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
