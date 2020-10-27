package movie.tickets.service.mapper;

import movie.tickets.model.Ticket;
import movie.tickets.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto toTicketResponseDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto(ticket.getId(),
                ticket.getMovieSession().getMovie().getTitle());
        return dto;
    }
}
