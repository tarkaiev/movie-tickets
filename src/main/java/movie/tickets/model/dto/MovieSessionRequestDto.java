package movie.tickets.model.dto;

import org.springframework.stereotype.Component;

@Component
public class MovieSessionRequestDto {
    private Long movieId;
    private String movieSessionDate;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieSessionDate() {
        return movieSessionDate;
    }

    public void setMovieSessionDate(String movieSessionDate) {
        this.movieSessionDate = movieSessionDate;
    }
}
