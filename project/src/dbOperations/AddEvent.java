package dbOperations;

import java.util.*;
import java.sql.*;

/**
 * Handles adding new events into the database
 * @author Sebastian Greenholtz
 */
public class AddEvent extends DatabaseHandler {

    private Connection conn;

    /**
     * Empty constructor
     */
    public AddEvent() {}

    /**
     * Constructor to set Properties variable
     * @param properties Application properties
     */
    public AddEvent(Properties properties) {
        super(properties);
        conn = getConnection();
    }

    /**
     * Inserts event into the database using the values in the Map
     * @param values The key is the name of the field, the value is the value
     */
    public void insertEvent(Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
