package movie.tickets.service.mapper;

import java.time.LocalDateTime;
import movie.tickets.model.MovieSession;
import movie.tickets.model.dto.MovieSessionRequestDto;
import movie.tickets.model.dto.MovieSessionResponseDto;
import movie.tickets.service.CinemaHallService;
import movie.tickets.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionDtoMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionDtoMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession fromRequestDto(MovieSessionRequestDto dto) {
        MovieSession session = new MovieSession();
        session.setMovie(movieService.get(dto.getMovieId()));
        session.setCinemaHall(cinemaHallService.get(dto.getCinemaHallId()));
        session.setShowTime(LocalDateTime.parse(dto.getMovieSessionDate()));
        return session;
    }

    public MovieSessionResponseDto toResponse(MovieSession session) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setMovieTitle(session.getMovie().getTitle());
        dto.setCinemaHallId(session.getCinemaHall().getId());
        dto.setMovieSessionId(session.getId());
        dto.setMovieSessionDate(session.getShowTime().toString());
        return dto;
    }
}
