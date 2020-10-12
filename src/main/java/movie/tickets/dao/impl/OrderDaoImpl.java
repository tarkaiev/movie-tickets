package movie.tickets.dao.impl;

import java.util.List;
import movie.tickets.dao.OrderDao;
import movie.tickets.exception.DataProcessingException;
import movie.tickets.lib.Dao;
import movie.tickets.model.Order;
import movie.tickets.model.User;
import movie.tickets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery(
                    "FROM Order o "
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
