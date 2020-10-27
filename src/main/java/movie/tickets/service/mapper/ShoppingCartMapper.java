package movie.tickets.service.mapper;

import java.util.stream.Collectors;
import movie.tickets.model.ShoppingCart;
import movie.tickets.model.dto.ShoppingCartResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto toResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setUserId(shoppingCart.getUser().getId());
        cartResponseDto.setTickets(shoppingCart.getTickets()
                .stream().map(ticketMapper::toTicketResponseDto)
                .collect(Collectors.toList()));
        return cartResponseDto;
    }
}
