package movie.tickets.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import movie.tickets.model.dto.MovieRequestDto;
import movie.tickets.model.dto.MovieResponseDto;
import movie.tickets.service.MovieService;
import movie.tickets.service.mapper.MovieDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieDtoMapper movieDtoMapper;

    public MovieController(MovieService movieService, MovieDtoMapper movieDtoMapper) {
        this.movieService = movieService;
        this.movieDtoMapper = movieDtoMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody @Valid MovieRequestDto dto) {
        movieService.add(movieDtoMapper.fromRequestDto(dto));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieDtoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
