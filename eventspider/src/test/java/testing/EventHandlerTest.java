package testing;

import eventspider.beans.EventBean;
import eventspider.beans.EventFactory;
import eventspider.database.EventHandler;
import eventspider.DAL.PropertiesLoader;
import eventspider.database.SessionFactoryProvider;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

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
        logger.info("");
        logger.info("***** STARTING TEST: EventHandlerTest ******");
    }

    @Before
    public void addEventsToDB() throws Exception {
        session.clear();
        clearDatabase();
        EventBean event1 = factory.createBean(123,
                "My Event", "http://event.com", "Test event",
                LocalDate.of(2016, 10, 13), "08:30:00", "09:00:00",
                "3802 Lien Rd", "Madison", "WI", "53704");

        EventBean event2 = factory.createBean(125, "Event3",
                "http://event.com", "Test event",
                LocalDate.of(2016, 9, 14), "05:00:00", "08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");

        EventBean event3 = factory.createBean(126, "Event4",
                "http://event.com", "Test event", LocalDate.of(2016, 9, 14),
                "06:30:00", "08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");
        session.beginTransaction();
        session.save(event1);
        session.save(event2);
        session.save(event3);
        session.getTransaction().commit();
    }

    private void clearDatabase() {
        String sql = "DELETE FROM Events";
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();

        sql = "DELETE FROM UserSavedEvents";
        session.beginTransaction();
        query = session.createSQLQuery(sql);
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
    public void saveEventToUser() throws Exception {
        assertTrue("Event could not be added to user",
                handler.saveEventToUser(1, 123));
    }

    @Test
    public void getEventByIDSingle() throws Exception {
        EventBean event = handler.getEventByID(123);
        assertEquals("Event could not be retrieved", "My Event", event.getTitle());
    }

    @Test
    public void getEventByIDList() throws Exception {
        List<Integer> eventIDs = new ArrayList<>();
        eventIDs.add(123);
        eventIDs.add(125);
        eventIDs.add(126);

        List<EventBean> beanList = handler.getEventByID(eventIDs);
        for (int i=0; i<eventIDs.size(); i++) {
            assertEquals(String.format("Event not added: %s", eventIDs.get(i)),
                    eventIDs.get(i), beanList.get(i).getEventId());
        }
    }

    @Test
    public void updateEventTitle() throws Exception {
        String newTitle = "My New Title";
        handler.updateEventTitle(newTitle, 123);
        EventBean event = handler.getEventByID(123);
        assertEquals("Title was not updated", newTitle, event.getTitle());
    }

    @Test
    public void deleteEvent() throws Exception {
        handler.deleteEvent(123);
        assertNull("Event was not deleted", handler.getEventByID(123));

    }

}