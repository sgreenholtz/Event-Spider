package beans;

import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

/**
 * Creates Event Beans from the results of a database search of the Events table
 * or from raw data (like a JSON)
 * @author Sebastian Greenholtz
 */
public class EventFactory {

    private ResultSet results;
    private Map<String, EventBean> eventMap;

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
    public Map<String, EventBean> getEventMap() {
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
                eventMap.put(results.getString("event_id"), createBean());
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
        eventMap.put(id, createBean(id, title, url, description,
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
     * Creates an Event bean from the current database row
     * @return Event Bean form the current row
     */
    private EventBean createBean() throws SQLException {
        EventBean event = new EventBean();
        event.setEventId(results.getString("event_id"));
        event.setTitle(results.getString("title"));
        event.setUrl(results.getString("url"));
        event.setDescription(results.getString("description"));
        event.setStartTime(formatDateTime(results.getString("start_time")));
        event.setStopTime(formatDateTime(results.getString("stop_time")));
        event.setVenueAddress(results.getString("venue_address"));
        event.setCity(results.getString("city"));
        event.setState(results.getString("state"));
        event.setPostalCode(results.getString("postal_code"));
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
        event.setEventId(id);
        event.setTitle(title);
        event.setUrl(url);
        event.setDescription(description);
        event.setStartTime(startTime);
        event.setStopTime(stopTime);
        event.setVenueAddress(venueAddress);
        event.setCity(city);
        event.setState(state);
        event.setPostalCode(postalCode);
        return event;
    }

    /**
     * Formats the date/time field from the yyyy-MM-dd hh:mm:ss.S format of teh
     * mySQL database to a more human readable EEE MMM d h:mm a format
     * @param mysqlFormattedDateTime String representing the date and time in MySql format
     * @return String representing the date and time in a human readable format
     */
    private String formatDateTime(String mysqlFormattedDateTime) {
        String formattedDateTime = "";
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            Date dateObjectRepresentation = inputFormatter.parse(mysqlFormattedDateTime);
            SimpleDateFormat outputFormatter = new SimpleDateFormat("EEEE, MMM d h:mm a");
            formattedDateTime = outputFormatter.format(dateObjectRepresentation);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDateTime;
    }


}
