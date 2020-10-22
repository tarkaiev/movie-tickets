package movie.tickets.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import movie.tickets.dao.OrderDao;
import movie.tickets.model.Order;
import movie.tickets.model.Ticket;
import movie.tickets.model.User;
import movie.tickets.service.OrderService;
import movie.tickets.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class OrderSerivceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    public OrderSerivceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

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
