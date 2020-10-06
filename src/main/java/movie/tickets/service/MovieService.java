package movie.tickets.service;

import java.util.List;
import movie.tickets.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
