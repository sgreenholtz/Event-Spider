package testing;

import eventspider.beans.EventBean;
import eventspider.beans.EventFactory;
import eventspider.beans.SearchBean;
import eventspider.database.DatabaseSearch;
import eventspider.database.SessionFactoryProvider;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the function of DatabaseSearch
 * @author Sebastian Greenholtz
 */
public class DatabaseSearchTest {

    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private DatabaseSearch searcher;
    private static EventFactory factory = new EventFactory();
    private static final Logger logger = Logger.getLogger(EventHandlerTest.class);

    @Before
    public void setUp() throws Exception {
        clearDatabase();
        EventBean event1 = factory.createBean(1, "Banana Bread Baking",
                "http://event.com", "Calls cats and creatures cantankerous",
                new LocalDate(2016, 10, 13), "08:30:00", "09:00:00",
                "3802 Lien Rd", "Madison", "WI", "53704");

        EventBean event2 = factory.createBean(2, "Cake and Caterpillars",
                "http://event.com", "Doesn't doubt dewy droplets",
                new LocalDate(2016, 9, 14), "05:00:00", "08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");

        EventBean event3 = factory.createBean(3, "Dragons, Flies, and Dragonflies",
                "http://event.com", "Even eagles eat every evening",
                new LocalDate(2016, 9, 14), "06:30:00", "08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");
        session.beginTransaction();
        session.save(event1);
        session.save(event2);
        session.save(event3);
        session.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        session.close();
    }

    private void clearDatabase() {
        String sql = "DELETE FROM Events";
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void searchByKeywordTitleTest() throws Exception {
        SearchBean search = new SearchBean();
        search.setKeyword("banana");
        searcher = new DatabaseSearch(search);
        List list = searcher.searchByKeywordOnly();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 1, list.size());
        EventBean event = (EventBean) list.get(0);
        assertTrue("Incorrect event returned", event.getTitle().equals(search.getKeyword()));
    }

    @Test
    public void searchByKeywordDescriptionTest() throws Exception {
        SearchBean search = new SearchBean();
        search.setKeyword("droplets");
        searcher = new DatabaseSearch(search);
        List list = searcher.searchByKeywordOnly();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 1, list.size());
        EventBean event = (EventBean) list.get(0);
        assertTrue("Incorrect event returned", event.getDescription().contains(search.getKeyword()));
    }

}