package movie.tickets.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import movie.tickets.annotation.EmailValidation;
import movie.tickets.annotation.FieldsValueMatch;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords are not equal"
)
public class UserRequestDto {
    @EmailValidation
    private String email;
    @NotNull
    @Size(min = 4)
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
