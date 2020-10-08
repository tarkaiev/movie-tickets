package movie.tickets.dao;

import movie.tickets.model.ShoppingCart;
import movie.tickets.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);
        
    ShoppingCart getByUser(User user);
        
    void update(ShoppingCart shoppingCart);
}
