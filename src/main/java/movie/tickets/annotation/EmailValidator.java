package movie.tickets.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext
            constraintValidatorContext) {
        return s != null && s.matches(EMAIL_PATTERN);
    }
}
