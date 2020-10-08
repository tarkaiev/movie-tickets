package movie.tickets.service;

import movie.tickets.model.MovieSession;
import movie.tickets.model.ShoppingCart;
import movie.tickets.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
