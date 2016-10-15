package com.eventspider.entity;

import com.eventspider.entity.*;
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
    public boolean eventExistsInDatabase(String id) {
        Event event = (Event) session.get(Event.class, id);
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
    public boolean addEvent(Event event) {
        session.beginTransaction();
        session.save(event);
        log.info("Event added: " + event.getEventId());
        session.getTransaction().commit();
        return true;
    }

    /**
     * Gets a single event from the database based on the ID
     * @param eventID Integer ID for the event
     * @return ResultSet with the single event
     */
    public Event getEventByID(String eventID) {
        Event event = (Event) session.get(Event.class, eventID);
        return event;
    }

    /**
     * Gets a list of the events given a list of IDs
     * @param eventIDs List of event IDs
     * @return List of Event objects
     */
    public List<Event> getEventByID(List<String> eventIDs) {
        List<Event> beanList = new ArrayList<Event>();
        for (String id : eventIDs) {
            Event event = (Event) session.get(Event.class, id);
            beanList.add(event);
        }
        return beanList;
    }

    /**
     * Update Title of an event
     * @param newTitle New title
     * @param eventID ID of event to change
     */
    public void updateEventTitle(String newTitle, String eventID) {
        session.beginTransaction();
        Event event = (Event) session.get(Event.class, eventID);
        event.setTitle(newTitle);
        log.info("Updated " + eventID + " to new title " + newTitle);
        session.getTransaction().commit();
    }

    /**
     * Delete event from database
     * @param eventID ID of event to delete
     */
    public void deleteEvent(String eventID) {
        session.beginTransaction();
        Event event = (Event) session.load(Event.class, eventID);
        session.delete(event);
        log.info("Event deleted: " + eventID);
        session.getTransaction().commit();
    }
}
