package beans;

import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

/**
 * Creates Event Beans from the results of a main.java.database search of the Events table
 * or from raw data (like a JSON)
 * @author Sebastian Greenholtz
 */
public class EventFactory {

    private ResultSet results;
    private Map<Integer, EventBean> eventMap;

    /**
     * Empty constructor, instantiates map
     */
    public EventFactory() {
        eventMap = new HashMap<>();
    }

    /**
     * Constructor with the search results
     * @param searchResults Database search results set
     */
    public EventFactory(ResultSet searchResults) {
        this();
        results = searchResults;
    }

    /**
     * Gets the event Map.
     * @return eventMap
     */
    public Map<Integer, EventBean> getEventMap() {
        return eventMap;
    }

    /**
     * Runs through the results set and calls the create bean
     * method on each row returned, then adds that bean to the Map with the
     * key being the event ID and the value being the bean object
     */
    public void createBeansMap() {
        try {
            while (results.next()) {
                eventMap.put(results.getInt("event_id"), createBean(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the map of ID -> Bean with String data for each
     * instance in the bean
     * @param id
     * @param title
     * @param url
     * @param description
     * @param startTime
     * @param stopTime
     * @param venueAddress
     * @param city
     * @param state
     * @param postalCode
     */
    public void createBeansMap(String id,
                               String title,
                               String url,
                               String description,
                               String startTime,
                               String stopTime,
                               String venueAddress,
                               String city,
                               String state,
                               String postalCode) {
        eventMap.put(createURLSafeID(id), createBean(id, title, url, description,
                                    startTime, stopTime, venueAddress,
                                    city, state, postalCode));
    }

    /**
     * Adds an already created Event bean into the map
     * @param event Event bean
     */
    public void updateBeansMap(EventBean event) {
        eventMap.put(event.getEventId(), event);
    }

    /**
     * Creates an Event bean from the current main.java.database row
     * @return Event Bean form the current row
     */
    public static EventBean createBean(ResultSet results) {
        EventBean event = new EventBean();
        try {
            event.setEventId(results.getInt("event_id"));
            event.setTitle(results.getString("title"));
            event.setUrl(results.getString("url"));
            event.setDescription(results.getString("description"));
            event.setStartTime(formatDateTimeMySql(results.getString("start_time")));
            if (results.getString("stop_time") == null) {
                event.setStopTime(null);
            } else {
                event.setStopTime(formatDateTimeMySql(results.getString("stop_time")));
            }
            event.setVenueAddress(results.getString("venue_address"));
            event.setCity(results.getString("city"));
            event.setState(results.getString("state"));
            event.setPostalCode(results.getString("postal_code"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    /**
     * Creates an Event bean using Strings for each instance
     * @param id
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
    public EventBean createBean(String id,
                                 String title,
                                 String url,
                                 String description,
                                 String startTime,
                                 String stopTime,
                                 String venueAddress,
                                 String city,
                                 String state,
                                 String postalCode) {
        EventBean event = new EventBean();
        event.setEventId(createURLSafeID(id));
        event.setTitle(title);
        event.setUrl(url);
        event.setDescription(description);
        event.setStartTime(formatDateTimeNoMiliSecond(startTime));
        if (!stopTime.equals("")) {
            event.setStopTime(formatDateTimeNoMiliSecond(stopTime));
        } else {
            event.setStopTime(null);
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return unformattedDateTime;
    }

    /**
     * Removes all non-numeric characters from the ID
     * @param id String ID that needs to be sanitized
     * @return Integer of ID
     */
    private Integer createURLSafeID(String id) {
        id = id.replaceAll("\\D", "");
        id = id.replaceFirst("0+", "");
        return new Integer(id.substring(0, 9));
    }


}
