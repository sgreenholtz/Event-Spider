package eventspider.database;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Super dao object to help with opening and closing sessions
 * @author Sebastian Greenholtz
 */
public class DAO {
    protected Session session;
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Emmpty constructor sets up session
     */
    public DAO() {
        log.info("** Opening new Hibernate session **");
        session = SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Create a new DAO with a given session
     * @param session Session already opened
     */
    public DAO (Session session) {
        this.session = session;
    }

    /**
     * Closes the Hibernate session
     */
    public void closeSession() {
        try {
            session.close();
            log.info("** Closing Hibernate session **");
        } catch (HibernateException e) {
            log.error(e);
        }
    }
}
