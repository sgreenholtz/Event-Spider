package eventspider.database;

import eventspider.beans.*;
import org.apache.log4j.Logger;
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
     * Empty constructor assigns session variable
     */
    public EventHandler() {
        session = SessionFactoryProvider.getSessionFactory().openSession();
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
        return true;
    }

    /**
     * Takes a list of the values from the form and inserts into the database
     * @param map Maps field names to values from form
     */
    public boolean addEvent(Map<String, String> map) {
        EventFactory factory = new EventFactory();
        EventBean event = factory.createBean(map.get("title"),
                map.get("url"), map.get("description"), map.get("startDateTime"),
                map.get("stopDateTime"), map.get("address"), map.get("city"),
                map.get("state"), map.get("postalCode"));

        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
        log.info("Event added: " + event.getEventId());
        return true;
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
     * Returns all the events in the database in a list
     * @return list of all event beans
     */
    public List<EventBean> getAllEvents() {
        return session.createCriteria(EventBean.class).list();
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
    }
}
