package eventspider.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 * Tool for triggering Hibernate indexing
 * @author Sebastian Greenholtz
 */
public class HibernateIndexing {

    public static Session session;

    public static void main(String[] args) throws Exception{
        HibernateIndexing indexer = new HibernateIndexing();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
    }

    public HibernateIndexing() {
        SessionFactory factory = SessionFactoryProvider.getSessionFactory();
        session = factory.openSession();
    }
}
