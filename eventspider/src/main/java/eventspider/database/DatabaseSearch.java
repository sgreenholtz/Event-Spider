package eventspider.database;

import eventspider.beans.*;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.*;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.*;

/**
 * Searches the database for events that match a given search
 * @author Sebastian Greenholtz
 */
public class DatabaseSearch {

    private SearchBean search;
    private static SearchFactory searchFactory;
    private static FullTextSession fullTextSession;

    public DatabaseSearch(Session session) {
        fullTextSession = Search.getFullTextSession(session);
        searchFactory = fullTextSession.getSearchFactory();
    }

    /**
     * Assigns search bean as instance variable and properties
     * object to the properties instance
     * @param search Search bean
     */
    public DatabaseSearch(SearchBean search) {
        this(SessionFactoryProvider.getSessionFactory().openSession());
        this.search = search;
    }

    /**
     * Set both the search bean and the session
     * @param session Hibernate session
     * @param search Search Bean
     */
    public DatabaseSearch(Session session, SearchBean search) {
        this(session);
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
        Query luceneQuery = eventQB.keyword()
                .onFields("title", "description")
                .matching(search.getKeyword())
                .createQuery();
        org.hibernate.Query query = fullTextSession.createFullTextQuery(luceneQuery, EventBean.class);
        return query.list();

    }

    /**
     * Gets all the events occurring on a single date.
     * @return List of events occurring on a given date
     * @throws Exception
     */
    public List<EventBean> searchBySingleDate() throws Exception {
        QueryBuilder eventQB = searchFactory.buildQueryBuilder().forEntity( EventBean.class ).get();
        Query luceneQuery = eventQB
                .keyword()
                .onField("startDate")
                .matching(search.getDateStart())
                .createQuery();
        org.hibernate.Query query = fullTextSession.createFullTextQuery(luceneQuery, EventBean.class);
        return query.list();
    }

    /**
     * Perform a search by keyword, filtered by date
     * @return List of Events that match the keyword and the date
     * @throws Exception
     */
    public List<EventBean> searchByKeywordAndSingleDate() throws Exception {
        QueryBuilder eventQB = searchFactory.buildQueryBuilder().forEntity( EventBean.class ).get();
        Query luceneQuery = eventQB
                .bool()
                .must(eventQB.keyword().onFields("title", "description").matching(search.getKeyword()).createQuery())
                .must(eventQB.keyword().onField("startDate").matching(search.getDateStart()).createQuery())
                .createQuery();
        org.hibernate.Query query = fullTextSession.createFullTextQuery(luceneQuery, EventBean.class);
        return query.list();

    }

}
