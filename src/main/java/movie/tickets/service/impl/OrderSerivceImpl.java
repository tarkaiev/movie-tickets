package movie.tickets.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import movie.tickets.dao.OrderDao;
import movie.tickets.lib.Inject;
import movie.tickets.lib.Service;
import movie.tickets.model.Order;
import movie.tickets.model.Ticket;
import movie.tickets.model.User;
import movie.tickets.service.OrderService;
import movie.tickets.service.ShoppingCartService;

@Service
public class OrderSerivceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(new ArrayList<>(tickets));
        order.setUser(user);
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getUsersOrders(user);
    }
}
