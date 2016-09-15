package database;

import beans.EventBean;
import beans.EventFactory;
import lucene.Indexer;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.util.*;

/**
 * Handles adding new events into the database and retrieving events from the
 * database using HibernateDAO
 * @author Sebastian Greenholtz
 */
public class EventHandler {

    private Session session;
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Constructor to set Properties variable
     */
    public EventHandler() {
        this.session = SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Checks whether a given ID already exists in the events database
     * @param id Event ID to check if already exists in the database
     * @return true if event ID exists in the database
     */
    public boolean eventExistsInDatabase(Integer id) {
        EventBean event = (EventBean) session.get(EventBean.class, id);
        if (event != null) {
            return true;
        }
        return false;
    }

    /**
     * Adds an event to the database based on an Event Bean
     * @param event Event Bean to add to the database
     * @return True if event was successfully added
     */
    public boolean addEvent(EventBean event) {
        session.beginTransaction();
        session.save(event);
        log.info("Event added: " + event.getEventId());
        session.getTransaction().commit();
        session.close();
        return true;
    }

    /**
     * Takes a list of the values from the form and inserts into the database
     * @param map Maps field names to values from form
     */
    public void insertEvent(Map<String, String> map) {
        EventFactory factory = new EventFactory();
        EventBean event = factory.createBean(UUID.randomUUID().toString(), map.get("title"),
                map.get("url"), map.get("description"), map.get("startDateTime"),
                map.get("stopDateTime"), map.get("address"), map.get("city"),
                map.get("state"), map.get("postalCode"));

        session.beginTransaction();
        session.save(event);
        log.info("Event added: " + event.getEventId());
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Saves a given event to a given user. Returns true if successfully added
     * @param userID Integer
     * @param eventID Integer
     * @return True if event is successfully added
     */
    public boolean saveEventToUser(Integer userID, Integer eventID, Properties properties) {
        String sql = properties.getProperty("save.event.to.user");
        sql = sql.replace("1", userID.toString());
        sql = sql.replace("2", eventID.toString());
        Query query = session.createQuery(sql);
        Integer result = query.executeUpdate();
        return (result != 0);
    }

    /**
     * Gets a single event from the database based on the ID
     * @param eventID Integer ID for the event
     * @return ResultSet with the single event
     */
    public EventBean getEventByID(Integer eventID) {
        EventBean event = (EventBean) session.get(EventBean.class, eventID);
        return event;
    }

    /**
     * Gets a list of the events given a list of IDs
     * @param eventIDs List of event IDs
     * @return List of EventBean objects
     */
    public List<EventBean> getEventByID(List<Integer> eventIDs) {
        List<EventBean> beanList = new ArrayList<EventBean>();
        for (Integer id : eventIDs) {
            EventBean event = (EventBean) session.get(EventBean.class, id);
            beanList.add(event);
        }
        return beanList;
    }

    /**
     * Adds the new event to the index of all events for Lucene searching
     * TODO: Make this method run in a separate thread
     * @param eventID Integer ID for event
     * @param title String title for event
     * @throws IOException
     */
    private void indexNewEvent(Integer eventID, String title, Properties properties) throws IOException {
        Indexer indexer = new Indexer(properties.getProperty("index.dir"));
        indexer.indexFields(eventID, title);
    }

    /**
     * Update Title of an event
     * @param newTitle New title
     * @param eventID ID of event to change
     */
    public void updateEventTitle(String newTitle, Integer eventID) {
        session.beginTransaction();
        EventBean event = (EventBean) session.get(EventBean.class, eventID);
        event.setTitle(newTitle);
        log.info("Updated " + eventID + " to new title " + newTitle);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Delete event from database
     * @param eventID ID of event to delete
     */
    public void deleteEvent(Integer eventID) {
        session.beginTransaction();
        EventBean event = (EventBean) session.load(EventBean.class, eventID);
        session.delete(event);
        log.info("Event deleted: " + eventID);
        session.getTransaction().commit();
        session.close();
    }
}
