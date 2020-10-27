package movie.tickets.service;

import movie.tickets.model.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);
}
