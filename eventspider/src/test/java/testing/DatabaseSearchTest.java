package testing;

import eventspider.beans.EventBean;
import eventspider.factories.EventFactory;
import eventspider.beans.SearchBean;
import eventspider.database.DatabaseSearch;
import eventspider.database.SessionFactoryProvider;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.LocalDate;
import org.junit.*;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the function of DatabaseSearch
 * @author Sebastian Greenholtz
 */
public class DatabaseSearchTest {

    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private DatabaseSearch searcher;
    private static SearchBean searchBean;
    private static EventFactory factory = new EventFactory();
    private static final Logger logger = Logger.getLogger(EventHandlerTest.class);

    @BeforeClass
    public static void setUp() throws Exception {
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

    @AfterClass
    public static void tearDown() throws Exception {
        session.close();
        deleteIndexDir();
    }

    @Before
    public void before() throws Exception {
        searchBean = new SearchBean();
        searchBean.setKeyword("");
        searchBean.setLocation("");
    }

    private static void clearDatabase() {
        String sql = "DELETE FROM Events";
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    private static void deleteIndexDir() throws Exception {
        String path = "/home/sebastian/Event-Spider/eventspider/indexes_test/eventspider.beans.EventBean";
        File dir = new File(path);
        if (dir.exists()) {
            FileUtils.deleteDirectory(dir);
        }
    }

    @Test
    public void searchByKeywordTitleTestSuccess() throws Exception {
        searchBean.setKeyword("banana");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 1, list.size());
        EventBean event = (EventBean) list.get(0);
        assertTrue(String.format("Expected %s Got %s", searchBean.getKeyword(), event.getTitle()),
                event.getTitle().toLowerCase().contains(searchBean.getKeyword()));
    }

    @Test
    public void searchByKeywordDescriptionTestFail() throws Exception {
        searchBean.setKeyword("pizza");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 0, list.size());

    }

    @Test
    public void searchByKeywordDescriptionTestSuccess() throws Exception {
        searchBean.setKeyword("droplets");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 1, list.size());
        EventBean event = (EventBean) list.get(0);
        assertTrue(String.format("Expected %s Got %s", searchBean.getKeyword(), event.getDescription()),
                event.getDescription().toLowerCase().contains(searchBean.getKeyword()));
    }

    @Test
    public void searchByKeywordTitleAndSingleDateTestSuccess() throws Exception {
        searchBean.setDateStart(new LocalDate(2016, 9, 14));
        searchBean.setKeyword("cake");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 1, list.size());
        EventBean event = (EventBean) list.get(0);
        assertTrue(String.format("Expected %s Got %s", searchBean.getKeyword(), event.getTitle()),
                event.getTitle().toLowerCase().contains(searchBean.getKeyword()));
    }

    @Test
    public void searchByKeywordDescriptionAndSingleDateTestSuccess() throws Exception {
        searchBean.setDateStart(new LocalDate(2016, 10, 13));
        searchBean.setKeyword("cats");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 1, list.size());
        EventBean event = (EventBean) list.get(0);
        assertTrue(String.format("Expected %s Got %s", searchBean.getKeyword(), event.getTitle()),
                event.getDescription().toLowerCase().contains(searchBean.getKeyword()));
    }

    @Test
    public void searchByKeywordAndSingleDateWrongDateFail() throws Exception {
        searchBean.setDateStart(new LocalDate(2000, 10, 13));
        searchBean.setKeyword("eagles");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 0, list.size());
    }

    @Test
    public void searchByKeywordAndSingleDateWrongKeywordFail() throws Exception {
        searchBean.setDateStart(new LocalDate(2016, 10, 13));
        searchBean.setKeyword("pizza");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not correctly returned", list);
        assertEquals("List of returned results is the wrong size", 0, list.size());
    }

    @Test
    public void searchByLocationSuccess() throws Exception {
        searchBean.setLocation("Madison");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not returned", list);
        assertEquals("List of results returned is the wrong size", 3, list.size());
    }

    @Test
    public void searchByLocationAndKeywordSuccess() throws Exception {
        searchBean.setKeyword("banana");
        searchBean.setLocation("Madison");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not returned", list);
        assertEquals("List of results returned is the wrong size", 1, list.size());
    }

    @Test
    public void searchByLocationFail() throws Exception {
        searchBean.setLocation("Nashville");
        searcher = new DatabaseSearch(session, searchBean);
        List list = searcher.performSearch();
        assertNotNull("Results not returned", list);
        assertEquals("List of results returned is the wrong size", 0, list.size());
    }

}