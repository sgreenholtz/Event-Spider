package database;

import beans.EventBean;
import lucene.Indexer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

/**
 * Handles adding new events into the database and retrieving events from the
 * database
 * @author Sebastian Greenholtz
 */
public class EventHandler {

    private Connection conn;
    private Properties properties;
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Constructor to set Properties variable
     * @param properties Application properties
     */
    public EventHandler(Properties properties) {
        this.properties = properties;
        conn = DatabaseHandler.getConnection();
    }

    /**
     * Checks whether a given ID already exists in the events database
     * @param id Event ID to check if already exists in the database
     * @return true if event ID exists in the database
     */
    public boolean eventExistsInDatabase(Integer id) {
        try {
            String sql = properties.getProperty("select.dup.id");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet idResults = statement.executeQuery();
            if (idResults.next()){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Create a PreparedStatement to insert an event into the table
     * from the values of an Event bean
     * @param event Event bean to add to the database
     * @return Prepared Statement to insert that event
     */
    private PreparedStatement createEventBeanAddStatement(EventBean event) {
        PreparedStatement statement = null;
        try {
            String sql = properties.getProperty("add.event.with.id");
            statement = conn.prepareStatement(sql);
            statement.setInt(1, event.getEventId());
            statement.setString(2, event.getTitle());
            statement.setString(3, event.getUrl());
            statement.setString(4, event.getDescription());
            statement.setString(5, formatDateTimeToMySql(event.getStartTime()));
            if (event.getStopTime() == null) {
                statement.setString(6, null);
            } else {
                statement.setString(6, formatDateTimeToMySql(event.getStopTime()));
            }
            statement.setString(7, event.getVenueAddress());
            statement.setString(8, event.getCity());
            statement.setString(9, event.getState());
            statement.setString(10, event.getPostalCode());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return statement;
    }

    /**
     * Adds an event to the database based on an Event Bean
     * @param event Event Bean to add to the database
     * @return True if event was successfully added
     */
    public boolean addEvent(EventBean event) throws RecordNotAddedException {
        Integer results = 0;
        try {
            PreparedStatement statement = createEventBeanAddStatement(event);
            results = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (results == 0) {
            return true;
        }
        throw new RecordNotAddedException();
    }

    /**
     * Takes a list of the values from the form and inserts into the database
     * @param formList List of values from the form
     */
    public void insertEvent(ArrayList<String> formList) {
        try {
            String sql = properties.getProperty("add.event.sql");
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i=1; i<= formList.size(); i++) {
                statement.setString(i, formList.get(i-1));
            }
            statement.executeUpdate();
            indexNewEvent(getNewEventID(), formList.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets an Integer of the last event ID, for auto-generated IDs
     * @return Integer of ID for the event just added to the database
     * @throws SQLException
     */
    private Integer getNewEventID() throws SQLException {
        PreparedStatement statement = conn.prepareStatement(properties.getProperty("last.insert.id"));
        ResultSet results = statement.executeQuery();
        results.last();
        return results.getInt("last_insert_id()");
    }

    /**
     * Formats Date/Time from the human readable EEE, MM d h:mm a to the
     * MySql format yyy-MM-dd hh:mm:ss.S
     * @param unFormattedDateTime Date/Time in unformatted, human readable style
     * @return MySql formatted Date/Time
     */
    private String formatDateTimeToMySql(String unFormattedDateTime) {
        String formattedDateTime = "";
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("EEEE, MMM d h:mm a");
            Date dateObjectRepresentation = inputFormatter.parse(unFormattedDateTime);
            SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            formattedDateTime = outputFormatter.format(dateObjectRepresentation);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDateTime;
    }

    /**
     * Saves a given event to a given user. Returns true if successfully added
     * @param userID
     * @param eventID
     * @return True if event is successfully aded
     */
    public boolean saveEventToUser(Integer userID, Integer eventID) {
        Integer rowsAdded = 0;
        try {
            String sql = properties.getProperty("save.event.to.user");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setInt(2, eventID);
            rowsAdded = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return (rowsAdded == 1);
    }

    /**
     * Gets a single event from the database based on the ID
     * @param eventID Integer ID for the event
     * @return ResultSet with the single event
     */
    public ResultSet getEventByID(Integer eventID) {
        ResultSet results = null;
        try {
            String sql = properties.getProperty("get.event.by.id") + ";";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, eventID);
            results = statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return results;
    }

    /**
     * Gets a ResultSet of the events given a list of IDs
     * @param eventIDs List of event IDs
     * @return
     */
    public ResultSet getEventByID(List<Integer> eventIDs) {
        ResultSet results = null;
        try {
            String sql = properties.getProperty("get.event.by.id");
            for (Integer id : eventIDs) {
                sql += " " + properties.getProperty("additional.id.select") + id;
            }
            PreparedStatement statement = conn.prepareStatement(sql + ";");
            statement.setInt(1, eventIDs.get(0));
            results = statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return results;
    }

    /**
     * Adds the new event to the index of all events for Lucene searching
     * TODO: Make this method run in a separate thread
     * @param eventID Integer ID for event
     * @param title String title for event
     * @throws IOException
     */
    private void indexNewEvent(Integer eventID, String title) throws IOException {
        Indexer indexer = new Indexer(properties.getProperty("index.dir"));
        indexer.indexFields(eventID, title);
    }
}
