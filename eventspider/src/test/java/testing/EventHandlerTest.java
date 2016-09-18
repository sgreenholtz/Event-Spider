package testing;

import beans.EventBean;
import beans.EventFactory;
import database.EventHandler;
import database.SessionFactoryProvider;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the EventHandler class
 * @author Sebastian Greenholtz
 */
public class EventHandlerTest {

    private Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private EventHandler handler = new EventHandler();

    @BeforeClass
    public void setUp() throws Exception {
        EventFactory factory = new EventFactory();
        EventBean event = factory.createBean("123",
                "My Event", "http://event.com", "Test event",
                "2016-09-14 06:30:00", "2016-09-14 08:30:00",
                "3802 Lien Rd", "Madison", "WI", "53704");
        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void eventExistsInDatabaseTest() throws Exception {
        assertTrue(handler.eventExistsInDatabase(123));
        assertFalse(handler.eventExistsInDatabase(12));
    }

    @Test
    public void addEventBeanTest() throws Exception {

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