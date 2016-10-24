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
    private QueryBuilder eventQB = searchFactory.buildQueryBuilder().forEntity( EventBean.class ).get();

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
     * TODO: Finish this method
     * @return
     * @throws Exception
     */
    public List<EventBean> performSearch() throws Exception {
        List<Query> queries = new ArrayList<>();

        if (search.getKeyword() != null) {
            queries.add(createKeywordQuery());
        }

        if (search.getLocation() != null) {
            queries.add(createLocationQuery());
        }

        if (search.getDateStart() != null) {
            queries.add(createStartDateQuery());
        }

        Query luceneQuery = eventQB
                .bool()
                .must(queries.get(0))
                .must(queries.get(1))
                .must(queries.get(2))
                .createQuery();
        org.hibernate.Query query = fullTextSession.createFullTextQuery(luceneQuery, EventBean.class);
        return query.list();
    }

    /**
     * Creates a query for the keyword search
     * @return Query for keyword in title or description
     * @throws Exception
     */
    private Query createKeywordQuery() throws Exception {
        return eventQB.keyword()
                .onFields("title", "description")
                .matching(search.getKeyword())
                .createQuery();

    }

    /**
     * Creates a query for the start date
     * @return Query to search by start date
     * @throws Exception
     */
    private Query createStartDateQuery() throws Exception {
        return eventQB.keyword().onField("startDate").matching(search.getDateStart()).createQuery();

    }

    /**
     * Creates a query for the location
     * @return Query for location search
     */
    private Query createLocationQuery() {
        return eventQB.keyword().onFields("venueAddress", "city", "state", "postalCode")
                .matching(search.getLocation()).createQuery();
    }

}
