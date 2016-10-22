package eventspider.database;

import eventspider.beans.*;
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
        if (event != null) {
            return true;
        }
        return false;
    }

    /**
     * Gets a single event from the database based on the ID
     *
     * @param eventID Integer ID for the event
     * @return ResultSet with the single event
     */
    public EventBean getEventByID(Integer eventID) {
        EventBean event = (EventBean) session.get(EventBean.class, eventID);
        return event;
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
}