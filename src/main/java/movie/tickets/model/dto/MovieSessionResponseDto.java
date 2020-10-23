package movie.tickets.model.dto;

public class MovieSessionResponseDto {
    private Long movieSessionId;
    private Long cinemaHallId;
    private String movieTitle;
    private String movieSessionDate;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
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
