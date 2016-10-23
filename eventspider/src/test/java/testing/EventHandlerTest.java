package testing;

import eventspider.beans.EventBean;
import eventspider.beans.EventFactory;
import eventspider.database.EventHandler;
import eventspider.database.SessionFactoryProvider;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.joda.time.LocalDate;
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
    private static String methodName;

    @BeforeClass
    public static void setUp() throws Exception {
        logger.info("");
        logger.info("***** STARTING TEST: EventHandlerTest ******");
    }

    @Before
    public void addEventsToDB() throws Exception {
        session.clear();
        clearDatabase();
        EventBean event1 = factory.createBean(1,
                "My Event", "http://event.com", "Test event",
                new LocalDate(2016, 10, 13), "08:30:00", "09:00:00",
                "3802 Lien Rd", "Madison", "WI", "53704");

        EventBean event2 = factory.createBean(2, "Event3",
                "http://event.com", "Test event",
                new LocalDate(2016, 9, 14), "05:00:00", "08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");

        EventBean event3 = factory.createBean(3, "Event4",
                "http://event.com", "Test event", new LocalDate(2016, 9, 14),
                "06:30:00", "08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");
        session.beginTransaction();
        session.save(event1);
        session.save(event2);
        session.save(event3);
        System.out.println(methodName);
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
        methodName = "eventExistsInDatabaseTest";
        boolean expectedTrue = handler.eventExistsInDatabase(1);
        boolean expectedFalse = handler.eventExistsInDatabase(12);
        assertTrue("Event 1 not found in DB", expectedTrue);
        assertFalse("Event 12 found in DB", expectedFalse);
    }

    @Test
    public void saveEventToUser() throws Exception {
        methodName = "saveEventToUser";
        assertTrue("Event could not be added to user",
                handler.saveEventToUser(1, 1));
    }

    @Test
    public void getEventByIDSingle() throws Exception {
        methodName = "getEventByIDSingle";
        EventBean event = handler.getEventByID(1);
        assertTrue("Event could not be retrieved", event.getTitle().equals("My Event"));
    }

    @Test
    public void getEventByIDList() throws Exception {
        methodName = "getEventByIDList";
        List<Integer> eventIDs = new ArrayList<>();
        eventIDs.add(1);
        eventIDs.add(2);
        eventIDs.add(3);

        List<EventBean> beanList = handler.getEventByID(eventIDs);
        for (int i=0; i<eventIDs.size(); i++) {
            assertEquals(String.format("Event not added: %s", eventIDs.get(i)),
                    eventIDs.get(i), beanList.get(i).getEventId());
        }
    }

    @Test
    public void updateEventTitle() throws Exception {
        methodName = "updateEventTitle";
        String newTitle = "My New Title";
        handler.updateEventTitle(newTitle, 1);
        EventBean event = handler.getEventByID(1);
        assertTrue("Title was not updated", event.getTitle().equals(newTitle));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteEvent() throws Exception {
        methodName = "deleteEvent";
        handler.deleteEvent(1);
        Object result = handler.getEventByID(1);

    }

}