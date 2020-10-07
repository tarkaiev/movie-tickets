package movie.tickets.security;

import java.util.Optional;
import movie.tickets.exception.AuthenticationException;
import movie.tickets.lib.Inject;
import movie.tickets.lib.Service;
import movie.tickets.model.User;
import movie.tickets.service.UserService;
import movie.tickets.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isEmpty()) {
            throw new AuthenticationException("No user with such login");
        }
        if (!HashUtil.hashPassword(password, userFromDb.get().getSalt())
                .equals(userFromDb.get().getPassword())) {
            throw new AuthenticationException("Incorrect password");
        }
        return userFromDb.get();
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }
}
