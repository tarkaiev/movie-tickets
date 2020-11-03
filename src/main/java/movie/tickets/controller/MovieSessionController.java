package movie.tickets.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import movie.tickets.model.dto.MovieSessionRequestDto;
import movie.tickets.model.dto.MovieSessionResponseDto;
import movie.tickets.service.MovieSessionService;
import movie.tickets.service.mapper.MovieSessionDtoMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionDtoMapper movieSessionDtoMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionDtoMapper movieSessionDtoMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionDtoMapper = movieSessionDtoMapper;
    }

    @PostMapping
    public void addMovieSession(@RequestBody @Valid MovieSessionRequestDto dto) {
        movieSessionService.add(movieSessionDtoMapper.fromRequestDto(dto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getMovieSessions(
            @RequestParam Long movieId, @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTime) {
        return movieSessionService.findAvailableSessions(movieId, dateTime).stream()
               .map(movieSessionDtoMapper::toResponse)
               .collect(Collectors.toList());
    }
}
