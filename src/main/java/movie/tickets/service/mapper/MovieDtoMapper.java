package movie.tickets.service.mapper;

import movie.tickets.model.Movie;
import movie.tickets.model.dto.MovieRequestDto;
import movie.tickets.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoMapper {
    public Movie fromRequestDto(MovieRequestDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        return movie;
    }

    public MovieResponseDto toResponseDto(Movie movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setMovieId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        return dto;
    }
}
