package movie.tickets.controller;

import javax.validation.Valid;
import movie.tickets.model.MovieSession;
import movie.tickets.model.ShoppingCart;
import movie.tickets.model.User;
import movie.tickets.model.dto.MovieSessionRequestDto;
import movie.tickets.model.dto.ShoppingCartResponseDto;
import movie.tickets.service.ShoppingCartService;
import movie.tickets.service.UserService;
import movie.tickets.service.mapper.MovieSessionDtoMapper;
import movie.tickets.service.mapper.ShoppingCartMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ShoppingCartResponseDto getByUserId(Authentication auth) {
        String userMail = ((UserDetails) auth.getPrincipal()).getUsername();
        ShoppingCart cart = shoppingCartService.getByUser(userService.findByEmail(userMail));
        return shoppingCartMapper.toResponseDto(cart);
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication auth,
                                @RequestBody @Valid MovieSessionRequestDto dto) {
        String userMail = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.findByEmail(userMail);
        MovieSession session = movieSessionDtoMapper.fromRequestDto(dto);
        shoppingCartService.addSession(session, user);
    }
}
