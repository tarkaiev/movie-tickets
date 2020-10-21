package movie.tickets.dao.impl;

import movie.tickets.dao.TicketDao;
import movie.tickets.exception.DataProcessingException;
import movie.tickets.model.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    private static final Logger logger = Logger.getLogger(TicketDaoImpl.class);

    private final SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        logger.info("Trying to add new ticket " + ticket + " to DB");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            logger.info(ticket + " successfully added to DB");
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
