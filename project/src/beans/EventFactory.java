package beans;

import java.sql.*;
import java.util.*;

/**
 * Creates Beans from the results of a database search of the Events table.
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
     * Creates an Event bean from the current database row
     * @return Event Bean form the current row
     */
    private EventBean createBean() throws SQLException {
        EventBean event = new EventBean();
        event.setEventId(results.getInt("event_id"));
        event.setTitle(results.getString("title"));
        event.setUrl(results.getString("url"));
        return event;
    }


}
