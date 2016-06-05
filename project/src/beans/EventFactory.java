package beans;

import java.sql.*;
import java.util.ArrayList;

/**
 * Creates Beans from the results of a database search of the Events table.
 * @author Sebastian Greenholtz
 */
public class EventFactory {

    private ResultSet results;
    private ArrayList<EventBean> eventList;

    /**
     * Empty constructor, instantiates array list
     */
    public EventFactory() {
        eventList = new ArrayList<>();
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
     * Returns eventList.
     * @return eventList
     */
    public ArrayList<EventBean> getEventList() {
        return eventList;
    }

    /**
     * Runs through the results set and calls the create bean
     * method on each row returned, then adds that bean to the list
     */
    public void createBeans() {
        try {
            while (results.next()) {
                eventList.add(createBean());
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
