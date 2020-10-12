package movie.tickets.dao;

import java.util.List;
import movie.tickets.model.Order;
import movie.tickets.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getUsersOrders(User user);
}
