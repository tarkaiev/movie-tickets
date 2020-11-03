package movie.tickets.model.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

public class MovieSessionRequestDto {
    @NotNull
    private Long movieId;
    @NotNull
    private Long cinemaHallId;
    @FutureOrPresent
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

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}
