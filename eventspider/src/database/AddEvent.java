package database;

import java.util.*;
import java.sql.*;

/**
 * Handles adding new events into the database
 * @author Sebastian Greenholtz
 */
public class AddEvent {

    private Connection conn;
    private Properties properties;

    /**
     * Empty constructor
     */
    public AddEvent() {}

    /**
     * Constructor to set Properties variable
     * @param properties Application properties
     */
    public AddEvent(Properties properties) {
        DatabaseHandler db = new DatabaseHandler(properties);
        this.properties = properties;
        conn = db.getConnection();
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
//            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
