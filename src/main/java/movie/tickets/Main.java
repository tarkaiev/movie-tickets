package movie.tickets;

import java.time.LocalDate;
import java.time.LocalDateTime;
import movie.tickets.exception.AuthenticationException;
import movie.tickets.lib.Injector;
import movie.tickets.model.CinemaHall;
import movie.tickets.model.Movie;
import movie.tickets.model.MovieSession;
import movie.tickets.model.User;
import movie.tickets.security.AuthenticationService;
import movie.tickets.service.CinemaHallService;
import movie.tickets.service.MovieService;
import movie.tickets.service.MovieSessionService;
import movie.tickets.service.OrderService;
import movie.tickets.service.ShoppingCartService;
import movie.tickets.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("movie.tickets");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie movie1 = new Movie();
        movie1.setTitle("Movie1");
        movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movieService.add(movie2);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setDescription("HALL NUMBER ONE");
        cinemaHallService.add(cinemaHall1);
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setDescription("HALL NUMBER TWO");
        cinemaHallService.add(cinemaHall2);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setCinemaHall(cinemaHall1);
        movieSession1.setMovie(movie1);
        movieSession1.setShowTime(LocalDateTime.of(2020, 10, 20, 15, 30));

        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);

        movieSessionService.add(movieSession1);
        MovieSession movieSession2 = new MovieSession();
        movieSession2.setCinemaHall(cinemaHall2);
        movieSession2.setMovie(movie2);
        movieSession2.setShowTime(LocalDateTime.of(2020, 10, 22, 15, 30));
        movieSessionService.add(movieSession2);
        System.out.println(movieSessionService
                .findAvailableSessions(1L, LocalDate.of(2020, 10, 20)));

        System.out.println(movieSessionService
                .findAvailableSessions(2L, LocalDate.of(2020, 10, 22)));

        UserService userService
                = (UserService) injector.getInstance(UserService.class);
        AuthenticationService authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User user1 = authenticationService.register("user1@gmail.com", "pass");
        User user2 = authenticationService.register("user2@gmail.com", "pass");
        System.out.println(userService.findByEmail("user1@gmail.com").toString());
        try {
            System.out.println(authenticationService.login("user2@gmail.com", "pass").toString());
        } catch (AuthenticationException e) {
            e.getMessage();
        }
        ShoppingCartService shoppingCartService
                = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession2, user1);
        System.out.println(shoppingCartService.getByUser(user1));
        shoppingCartService.clear(shoppingCartService.getByUser(user1));
        shoppingCartService.addSession(movieSession2, user1);
        System.out.println(shoppingCartService.getByUser(user1));

        OrderService orderService
                = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user1).getTickets(), user1);
        orderService.getOrderHistory(user1).forEach(System.out::println);
    }
}
