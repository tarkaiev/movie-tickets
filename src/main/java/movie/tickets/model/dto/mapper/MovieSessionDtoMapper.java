package movie.tickets.model.dto.mapper;

import movie.tickets.model.MovieSession;
import movie.tickets.model.dto.MovieSessionRequestDto;
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

    private MovieSession movieSessionFromRequestDto(MovieSessionRequestDto dto) {
        MovieSession session = new MovieSession();

        return session;
    }
}
