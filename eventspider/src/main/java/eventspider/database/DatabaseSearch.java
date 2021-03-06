package eventspider.database;

import eventspider.beans.*;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.*;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.MustJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.*;

/**
 * Searches the database for events that match a given search
 * @author Sebastian Greenholtz
 */
public class DatabaseSearch extends DAO {

    private SearchBean search;
    private static SearchFactory searchFactory;
    private static FullTextSession fullTextSession;
    private QueryBuilder eventQB;

    /**
     * Creates a new full text session from the given session
     * @param session Session already open
     */
    public DatabaseSearch(Session session) {
        super(session);
        fullTextSession = Search.getFullTextSession(super.session);
        searchFactory = fullTextSession.getSearchFactory();
        eventQB = searchFactory.buildQueryBuilder().forEntity( EventBean.class ).get();
    }

    /**
     * Assigns search bean as instance variable and properties
     * object to the properties instance
     * @param search Search bean
     */
    public DatabaseSearch(SearchBean search) {
        super();
        fullTextSession = Search.getFullTextSession(super.session);
        searchFactory = fullTextSession.getSearchFactory();
        eventQB = searchFactory.buildQueryBuilder().forEntity( EventBean.class ).get();
        this.search = search;
    }

    /**
     * Set both the search bean and the session
     * @param session Hibernate session
     * @param search Search Bean
     */
    public DatabaseSearch(Session session, SearchBean search) {
        super(session);
        fullTextSession = Search.getFullTextSession(super.session);
        searchFactory = fullTextSession.getSearchFactory();
        eventQB = searchFactory.buildQueryBuilder().forEntity( EventBean.class ).get();
        this.search = search;
    }

    /**
     * Performs the full text search using the boolean junction
     * query created by createQuery()
     * @return List of Event Beans returned by the search
     * @throws Exception
     */
    public List<EventBean> performSearch() throws Exception {
        org.hibernate.Query query = fullTextSession
                .createFullTextQuery(createQuery(), EventBean.class);
        return query.list();
    }

    /**
     * Creates a query for each of the fields specified
     * within the search bean and uses them to create a
     * junction query
     * @return Lucene Query with the subqueries joined using a boolean junction
     * @throws Exception
     */
    private Query createQuery() throws Exception {
        List<Query> queries = new ArrayList<>();

        if (!(search.getKeyword().equals(""))) {
            queries.add(createKeywordQuery());
        }

        if (!(search.getLocation().equals(""))) {
            queries.add(createLocationQuery());
        }

        if (search.getDateStart() != null) {
            queries.add(createStartDateQuery());
        }

        MustJunction must = eventQB.bool().must(queries.get(0));
        for (int i=1;i<queries.size(); i++) {
            must = must.must(queries.get(i));
        }
        return must.createQuery();
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
        return eventQB.keyword()
                .onField("startDate")
                .matching(search.getDateStart())
                .createQuery();

    }

    /**
     * Creates a query for the location
     * @return Query for location search
     */
    private Query createLocationQuery() {
        return eventQB.keyword()
                .onFields("city", "state", "postalCode")
                .matching(search.getLocation())
                .createQuery();
    }

}
