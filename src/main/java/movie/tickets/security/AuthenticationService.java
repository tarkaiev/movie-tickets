package movie.tickets.security;

import movie.tickets.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
