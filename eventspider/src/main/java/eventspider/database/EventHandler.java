package eventspider.database;

import eventspider.beans.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDate;
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
     *
     * @param id Event ID to check if already exists in the database
     * @return true if event ID exists in the database
     */
    public boolean eventExistsInDatabase(Integer id) {
        EventBean event = (EventBean) session.get(EventBean.class, id);
        return (event != null);
    }

    /**
     * Gets a single event from the database based on the ID
     *
     * @param eventID Integer ID for the event
     * @return ResultSet with the single event
     */
    public EventBean getEventByID(Integer eventID) {
        return (EventBean) session.get(EventBean.class, eventID);
    }

    /**
     * Gets a list of the events given a list of IDs
     *
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
     * Saves a given event to a given user. Returns true if successfully added
     *
     * @param userID  Integer
     * @param eventID Integer
     * @return True if event is successfully added
     */
    public boolean saveEventToUser(Integer userID, Integer eventID) {
        UserSavedEvents savedEvent = new UserSavedEvents();
        savedEvent.setEventID(eventID);
        savedEvent.setUserID(userID);
        session.beginTransaction();
        session.save(savedEvent);
        session.getTransaction().commit();
        log.info(String.format("Event %s saved to user %s", eventID, userID));
        return true;
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

    /**
     * Gets Event ID based on the event title
     * @param title String title
     * @return Integer ID of the event
     */
    public Integer getEventIDByTitle(String title) {
        String sql = String.format("SELECT eventId FROM EventBean WHERE title = '%s'", title);
        log.info("Select statement:" + sql);
        List<Integer> list = getDBList(sql);
        return list.get(0);
    }

    /**
     * Gets list of Event IDs based on list of event titles
     * @param titles List of String event titles
     * @return List of Integer IDs of the events
     */
    public List<Integer> getEventIDByTitle(List<String> titles) {
        String sql = String.format("SELECT eventId FROM EventBean WHERE title = '%s'", titles.get(0));
        for (int i=1; i<titles.size(); i++) {
           sql += String.format(" OR title = '%s'", titles.get(i));
        }
        log.info("Select statement:" + sql);
        return getDBList(sql);
    }

    /**
     * Performs a SQL transaction and returns a list of the results
     * @param sql String sql statement for a select
     * @return Untyped list of the results
     */
    private List getDBList(String sql) {
        session.beginTransaction();
        List list = session.createQuery(sql).list();
        session.getTransaction().commit();
        return list;
    }

    /**
     * Performs a delete on the database of events with a start date
     * older than the date passed to the function
     * @param date LocalDate as the upper bound (exclusive) of the deletion
     * @return true if the deletion was successful
     */
    public boolean deleteOldItems(LocalDate date) {

        return true;
    }
}