package movie.tickets.dao;

import java.util.List;
import movie.tickets.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);
    
    List<Movie> getAll();

    Movie get(Long movieId);
}
