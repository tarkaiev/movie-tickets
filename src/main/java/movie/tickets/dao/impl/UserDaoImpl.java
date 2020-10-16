package movie.tickets.dao.impl;

import java.util.Optional;
import movie.tickets.dao.UserDao;
import movie.tickets.exception.DataProcessingException;
import movie.tickets.lib.Dao;
import movie.tickets.model.User;
import movie.tickets.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User add(User user) {
        logger.info("Trying to add user " + user + " to DB");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.info(user + " successfully added to DB");
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new user " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            logger.info("Trying to find user with email " + email);
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResultOptional();
        }
    }
}
