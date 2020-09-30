package movie.tickets.service;

import java.util.List;
import movie.tickets.dao.MovieDao;
import movie.tickets.lib.Inject;
import movie.tickets.lib.Service;
import movie.tickets.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
