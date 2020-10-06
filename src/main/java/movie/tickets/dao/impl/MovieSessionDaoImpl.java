package movie.tickets.dao.impl;

import java.time.LocalDate;
import java.util.List;
import movie.tickets.dao.MovieSessionDao;
import movie.tickets.lib.Dao;
import movie.tickets.model.MovieSession;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return null;
    }

    @Override
    public MovieSession add(MovieSession session) {
        return null;
    }
}
