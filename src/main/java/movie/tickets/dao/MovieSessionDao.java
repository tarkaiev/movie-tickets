package movie.tickets.dao;

import java.time.LocalDate;
import java.util.List;
import movie.tickets.model.MovieSession;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}
