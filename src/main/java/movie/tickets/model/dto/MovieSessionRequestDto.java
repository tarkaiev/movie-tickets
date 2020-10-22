package movie.tickets.model.dto;

public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
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
