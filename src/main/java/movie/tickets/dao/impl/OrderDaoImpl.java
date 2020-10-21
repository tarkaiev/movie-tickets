package movie.tickets.dao.impl;

import java.util.List;
import movie.tickets.dao.OrderDao;
import movie.tickets.exception.DataProcessingException;
import movie.tickets.model.Order;
import movie.tickets.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);

    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        logger.info("Trying to add new order " + order + " to DB");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            logger.info(order + " successfully added to DB");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new order " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getUsersOrders(User user) {
        logger.info("Trying to get all orders from user " + user);
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery(
                    "SELECT DISTINCT o FROM Order o "
                            + "LEFT JOIN FETCH o.tickets "
                            + "JOIN FETCH o.user "
                            + "WHERE o.user = :user",
                    Order.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get shopping cart by user " + user, e);
        }
    }
}
