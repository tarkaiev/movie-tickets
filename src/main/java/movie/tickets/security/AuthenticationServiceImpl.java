package movie.tickets.security;

import java.util.Optional;
import movie.tickets.exception.AuthenticationException;
import movie.tickets.lib.Inject;
import movie.tickets.lib.Service;
import movie.tickets.model.User;
import movie.tickets.service.ShoppingCartService;
import movie.tickets.service.UserService;
import movie.tickets.util.HashUtil;
import org.apache.log4j.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        logger.info("User with email " + email + " is trying to login");
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isEmpty() || !HashUtil.hashPassword(password, userFromDb.get().getSalt())
                .equals(userFromDb.get().getPassword())) {
            throw new AuthenticationException("Incorrect login or password");
        }
        logger.info("User with email " + email + " successfully logged in");
        return userFromDb.get();
    }

    @Override
    public User register(String email, String password) {
        logger.info("User with email " + email + " is trying to pass registration");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        logger.info("User with email " + email + " successfully registered");
        return user;
    }
}
