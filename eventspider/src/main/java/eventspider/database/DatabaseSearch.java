package eventspider.database;

import eventspider.beans.*;
import eventspider.database.EventHandler;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.*;

/**
 * Searches the database for events that match a given search
 * @author Sebastian Greenholtz
 */
public class DatabaseSearch {

    private SearchBean search;
    private static SearchFactory searchFactory;
    private static Session session;

    public DatabaseSearch() {
        session = SessionFactoryProvider.getSessionFactory().openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        searchFactory = fullTextSession.getSearchFactory();
    }

    /**
     * Assigns search bean as instance variable and properties
     * object to the properties instance
     * @param search Search bean
     */
    public DatabaseSearch(SearchBean search) {
        this();
        this.search = search;
    }

    /**
     * Performs a search in the database using the EventHandler DAO object
     * and returns a list of event beans
     * @return List of event beans with the given keyword in the title or description
     * @throws Exception
     */
    public List<EventBean> searchByKeywordOnly() throws Exception {
        QueryBuilder eventQB = searchFactory.buildQueryBuilder().forEntity( EventBean.class ).get();
    }


}
