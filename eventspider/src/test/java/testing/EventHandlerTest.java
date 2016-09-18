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

    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private EventHandler handler = new EventHandler();
    private static EventFactory factory = new EventFactory();

    @BeforeClass
    public static void setUp() throws Exception {
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
        assertTrue("Event added not found in DB", (bean.equals((EventBean) session.get(EventBean.class, "124"))));
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