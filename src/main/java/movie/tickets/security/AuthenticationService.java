package movie.tickets.security;

import movie.tickets.exception.AuthenticationException;
import movie.tickets.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
