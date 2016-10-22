package eventspider.beans;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;
import java.util.List;

/**
 * Creates Event Beans from the results of a main.java.database search of the Events table
 * or from raw data (like a JSON)
 * @author Sebastian Greenholtz
 */
public class EventFactory {

    private Map<Integer, EventBean> eventMap;
    private static final Logger logger = Logger.getLogger(EventFactory.class);

    /**
     * Empty constructor, instantiates map
     */
    public EventFactory() {
        eventMap = new HashMap<>();
    }

    /**
     * Gets the event Map.
     * @return eventMap
     */
    public Map<Integer, EventBean> getEventMap() {
        return eventMap;
    }

    /**
     * Adds an already created Event bean into the map
     * @param event Event bean
     */
    public void updateBeansMap(EventBean event) {
        eventMap.put(event.getEventId(), event);
    }


    /**
     * Creates map of id->bean using a list of event bean objects
     * @param beanList List of event bean objects
     */
    public void createBeansMap(List<EventBean> beanList) {
        for (EventBean bean : beanList) {
            eventMap.put(bean.getEventId(), bean);
        }
    }

    /**
     * Creates an Event bean using Strings for each instance
     * @param title
     * @param url
     * @param description
     * @param startTime
     * @param stopTime Can be left blank if no stop time is indicated
     * @param venueAddress
     * @param city
     * @param state
     * @param postalCode Either zip code or Canadian Postal code
     * @return Event bean
     */
    public EventBean createBean(String title,
                                String url,
                                String description,
                                String startTime,
                                String stopTime,
                                String venueAddress,
                                String city,
                                String state,
                                String postalCode) {
        EventBean event = new EventBean();
        event.setTitle(title);
        event.setUrl(url);
        event.setDescription(description);
        event.setStartTime((startTime));
        if (!stopTime.equals("")) {
            event.setEndTime((stopTime));
        } else {
            event.setEndTime(null);
        }
        event.setVenueAddress(venueAddress);
        event.setCity(city);
        event.setState(state);
        event.setPostalCode(postalCode);
        return event;
    }

    /**
     * Creates an Event bean with all of the instance variables
     * including the ID as a int
     * @param id Integer id for the event
     * @param title
     * @param url
     * @param description
     * @param startTime
     * @param stopTime Can be left blank if no stop time is indicated
     * @param venueAddress
     * @param city
     * @param state
     * @param postalCode Either zip code or Canadian Postal code
     * @return Event bean
     */
    public EventBean createBean(Integer id, String title,
                                String url,
                                String description,
                                String startTime,
                                String stopTime,
                                String venueAddress,
                                String city,
                                String state,
                                String postalCode) {
        EventBean event = new EventBean();
        event.setEventId(id);
        event.setTitle(title);
        event.setUrl(url);
        event.setDescription(description);
        event.setStartTime((startTime));
        if (!stopTime.equals("")) {
            event.setEndTime((stopTime));
        } else {
            event.setEndTime(null);
        }
        event.setVenueAddress(venueAddress);
        event.setCity(city);
        event.setState(state);
        event.setPostalCode(postalCode);
        return event;
    }

    /**
     * Formats the date/time field from the yyyy-MM-dd hh:mm:ss.S format of the
     * mySQL main.java.database to a more human readable EEE MMM d h:mm a format. If the date
     * is already in a readable format, the date is returned as originally formatted
     * @param mysqlFormattedDateTime String representing the date and time in MySql format
     * @return String representing the date and time in a human readable format
     */
    private static String formatDateTimeMySql(String mysqlFormattedDateTime) {
        String formattedDateTime = "";
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            Date dateObjectRepresentation = inputFormatter.parse(mysqlFormattedDateTime);
            if (dateObjectRepresentation == null) {
                return mysqlFormattedDateTime;
            }
            SimpleDateFormat outputFormatter = new SimpleDateFormat("EEEE, MMM d h:mm a");
            formattedDateTime = outputFormatter.format(dateObjectRepresentation);
        } catch (ParseException e) {
            logger.error(e.getStackTrace());
        }
        return formattedDateTime;
    }

    /**
     * Formats the date/time field from the yyyy-MM-dd hh:mm:ss format
     * to a more human readable EEE MMM d h:mm a format
     * @param formattedDateTime String representing the date and time in formatted style
     * @return String representing the date and time in a human readable format
     */
    private String formatDateTimeNoMiliSecond(String formattedDateTime) {
        String unformattedDateTime = "";
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dateObjectRepresentation = inputFormatter.parse(formattedDateTime);
            SimpleDateFormat outputFormatter = new SimpleDateFormat("EEEE, MMM d h:mm a");
            unformattedDateTime = outputFormatter.format(dateObjectRepresentation);
        } catch (ParseException e) {
            logger.error(e.getStackTrace());
        }
        return unformattedDateTime;
    }

}