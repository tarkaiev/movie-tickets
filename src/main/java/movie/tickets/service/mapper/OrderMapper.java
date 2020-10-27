package movie.tickets.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import movie.tickets.model.Order;
import movie.tickets.model.dto.OrderResponseDto;
import movie.tickets.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto toOrderResponse(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setUserId(order.getUser().getId());
        dto.setUserEmail(order.getUser().getEmail());
        List<TicketResponseDto> tickets = order.getTickets().stream()
                .map(ticket -> new TicketResponseDto(ticket.getId(),
                        ticket.getMovieSession().getMovie().getTitle()))
                .collect(Collectors.toList());
        dto.setTickets(tickets);
        return dto;
    }
}
