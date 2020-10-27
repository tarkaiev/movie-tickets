package movie.tickets.controller;

import movie.tickets.model.MovieSession;
import movie.tickets.model.ShoppingCart;
import movie.tickets.model.User;
import movie.tickets.model.dto.MovieSessionRequestDto;
import movie.tickets.model.dto.ShoppingCartResponseDto;
import movie.tickets.service.ShoppingCartService;
import movie.tickets.service.UserService;
import movie.tickets.service.mapper.MovieSessionDtoMapper;
import movie.tickets.service.mapper.ShoppingCartMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionDtoMapper movieSessionDtoMapper;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(UserService userService,
                                  ShoppingCartService shoppingCartService,
                                  MovieSessionDtoMapper movieSessionDtoMapper,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.movieSessionDtoMapper = movieSessionDtoMapper;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUserId(@RequestParam String email) {
        ShoppingCart cart = shoppingCartService.getByUser(userService.findByEmail(email));
        return shoppingCartMapper.toResponseDto(cart);
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam String email,
                                @RequestBody MovieSessionRequestDto dto) {
        User user = userService.findByEmail(email);
        MovieSession session = movieSessionDtoMapper.fromRequestDto(dto);
        shoppingCartService.addSession(session, user);
    }
}
