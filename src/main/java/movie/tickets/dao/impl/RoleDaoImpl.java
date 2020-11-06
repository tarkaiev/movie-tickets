package movie.tickets.dao.impl;

import movie.tickets.dao.RoleDao;
import movie.tickets.exception.DataProcessingException;
import movie.tickets.model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);

    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            logger.info(role + " successfully added to DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new role " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        logger.info("Trying to get role by rolename " + roleName);
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Role "
                    + "WHERE roleName = :name", Role.class)
                    .setParameter("name", Role.RoleName.valueOf(roleName))
                    .getSingleResult();
        }
    }
}
