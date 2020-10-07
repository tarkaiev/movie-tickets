package movie.tickets.service;

import java.util.Optional;
import movie.tickets.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
