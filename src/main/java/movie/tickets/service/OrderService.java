package movie.tickets.service;

import java.util.List;
import movie.tickets.model.Order;
import movie.tickets.model.Ticket;
import movie.tickets.model.User;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
