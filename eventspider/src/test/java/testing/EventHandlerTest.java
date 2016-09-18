package testing;

import beans.EventBean;
import beans.EventFactory;
import database.EventHandler;
import database.SessionFactoryProvider;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the EventHandler class
 * @author Sebastian Greenholtz
 */
public class EventHandlerTest {

    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private EventHandler handler = new EventHandler();
    private static EventFactory factory = new EventFactory();
    private static final Logger logger = Logger.getLogger(EventHandlerTest.class);

    @BeforeClass
    public static void setUp() throws Exception {
        logger.info("***** STARTING TEST: EventHandlerTest ******");
        clearDatabase();
        EventBean event = factory.createBean("123",
                "My Event", "http://event.com", "Test event",
                "2016-09-14 06:30:00", "2016-09-14 08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");
        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
    }

    public static void clearDatabase() {
        String sql = "DELETE FROM Events";
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        session.close();
    }

    @Test
    public void eventExistsInDatabaseTest() throws Exception {
        assertTrue("Event 123 not found in DB", handler.eventExistsInDatabase(123));
        assertFalse("Event 12 found in DB", handler.eventExistsInDatabase(12));
    }

    @Test
    public void addEventBeanTest() throws Exception {
        EventBean bean = factory.createBean("124", "Event2",
                "http://event.com", "Test event",
                "2016-09-14 06:30:00", "2016-09-14 08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");
        assertTrue("Event not added to DB", handler.addEvent(bean));

        EventBean retrievedBean = (EventBean) session.get(EventBean.class, 124);
        assertTrue("Event added not found in DB", (124 == retrievedBean.getEventId()));
    }

    @Test
    public void addEventMapTest() throws Exception {

    }

    @Test
    public void saveEventToUser() throws Exception {

    }

    @Test
    public void getEventByID() throws Exception {

    }

    @Test
    public void getEventByID1() throws Exception {

    }

    @Test
    public void updateEventTitle() throws Exception {

    }

    @Test
    public void deleteEvent() throws Exception {

    }

}