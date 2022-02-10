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
        if (password == null) {
            return false;
        } else if (password.length() > 6) {
            return false;
        } else if (!password.matches("[A-Za-z0-9]*")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
