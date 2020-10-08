package movie.tickets.dao.impl;

import movie.tickets.dao.TicketDao;
import movie.tickets.exception.DataProcessingException;
import movie.tickets.lib.Dao;
import movie.tickets.model.Ticket;
import movie.tickets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new ticket " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}