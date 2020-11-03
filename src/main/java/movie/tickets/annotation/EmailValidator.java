package movie.tickets.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public boolean isValid(String inputEmail, ConstraintValidatorContext
            constraintValidatorContext) {
        return inputEmail != null && inputEmail.matches(EMAIL_PATTERN);
    }
}
