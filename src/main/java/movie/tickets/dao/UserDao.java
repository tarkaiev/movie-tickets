package movie.tickets.dao;

import movie.tickets.model.User;

import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
