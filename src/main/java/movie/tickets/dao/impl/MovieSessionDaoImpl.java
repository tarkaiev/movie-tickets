package movie.tickets.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import movie.tickets.dao.MovieSessionDao;
import movie.tickets.exception.DataProcessingException;
import movie.tickets.lib.Dao;
import movie.tickets.model.MovieSession;
import movie.tickets.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger logger = Logger.getLogger(MovieSessionDaoImpl.class);

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        logger.info("Trying to find sessions with movie id = " + movieId
                + " and date " + date);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "FROM MovieSession WHERE movie_id = :movieId "
                            + "AND showTime BETWEEN :startTime AND :endTime",
                    MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("startTime", date.atStartOfDay());
            query.setParameter("endTime", date.atTime(LocalTime.MAX));
            return query.getResultList();
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        logger.info("Trying to add new movie session " + movieSession + " to DB");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            logger.info(movieSession + " successfully added to DB");
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new movie session " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
