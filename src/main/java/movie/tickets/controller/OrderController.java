package movie.tickets.controller;

import java.util.List;
import java.util.stream.Collectors;
import movie.tickets.model.ShoppingCart;
import movie.tickets.model.Ticket;
import movie.tickets.model.User;
import movie.tickets.model.dto.OrderResponseDto;
import movie.tickets.service.OrderService;
import movie.tickets.service.ShoppingCartService;
import movie.tickets.service.UserService;
import movie.tickets.service.mapper.OrderMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService,
                           UserService userService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam String email) {
        User user = userService.findByEmail(email);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        List<Ticket> tickets = shoppingCart.getTickets();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(@RequestParam String email) {
        return orderService.getOrderHistory(userService.findByEmail(email)).stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }
}
