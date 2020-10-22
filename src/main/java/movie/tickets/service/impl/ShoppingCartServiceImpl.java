package movie.tickets.service.impl;

import java.util.ArrayList;
import movie.tickets.dao.ShoppingCartDao;
import movie.tickets.dao.TicketDao;
import movie.tickets.model.MovieSession;
import movie.tickets.model.ShoppingCart;
import movie.tickets.model.Ticket;
import movie.tickets.model.User;
import movie.tickets.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        ShoppingCart cart = shoppingCartDao.getByUser(user);
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        Ticket addedTicket = ticketDao.add(ticket);
        cart.getTickets().add(addedTicket);
        shoppingCartDao.update(cart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setTickets(new ArrayList<>());
        cart.setUser(user);
        shoppingCartDao.add(cart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.update(shoppingCart);
    }
}
