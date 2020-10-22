package movie.tickets.dao.impl;

import java.util.List;
import movie.tickets.dao.MovieDao;
import movie.tickets.exception.DataProcessingException;
import movie.tickets.model.Movie;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private static final Logger logger = Logger.getLogger(MovieDaoImpl.class);

    private final SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Movie add(Movie movie) {
        logger.info("Trying to add new movie " + movie + " to DB");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            logger.info(movie + " successfully added to DB");
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add movie " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        logger.info("Trying to get all movies from DB");
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> getAllMoviesQuery = session.createQuery("from Movie", Movie.class);
            return getAllMoviesQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movies", e);
        }
    }

    @Override
    public Movie get(Long movieId) {
        logger.info("Trying to get movie with id " + movieId);
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query
                    = session.createQuery("from Movie where id = : id", Movie.class);
            query.setParameter("id", movieId);
            return query.getSingleResult();
        }
    }
}
