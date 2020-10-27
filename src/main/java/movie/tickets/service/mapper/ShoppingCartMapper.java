package movie.tickets.service.mapper;

import java.util.stream.Collectors;
import movie.tickets.model.ShoppingCart;
import movie.tickets.model.Ticket;
import movie.tickets.model.dto.ShoppingCartResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto toResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setUserId(shoppingCart.getUser().getId());
        cartResponseDto.setTicketIds(shoppingCart.getTickets()
                .stream().map(Ticket::getId)
                .collect(Collectors.toList()));
        return cartResponseDto;
    }
}
