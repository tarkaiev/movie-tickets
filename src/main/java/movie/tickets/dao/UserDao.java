package movie.tickets.dao;

import java.util.Optional;
import movie.tickets.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
