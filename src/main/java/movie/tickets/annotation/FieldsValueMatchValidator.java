package movie.tickets.annotation;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import movie.tickets.model.dto.UserRequestDto;

public class FieldsValueMatchValidator implements
        ConstraintValidator<FieldsValueMatch, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto dto, ConstraintValidatorContext context) {
        Object fieldValue = dto.getPassword();
        Object fieldMatchValue = dto.getRepeatPassword();
        return Objects.equals(fieldValue, fieldMatchValue);
    }
}
