package testing;

import database.DatabaseHandler;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Loads up the main.java.database for use in testing
 * @author Sebastian Greenholtz
 */
public class TestDatabaseHandler {

    private Properties properties;
    private Connection conn;

    /**
     * Constructor loads up the properties file for testing
     * and gets a main.java.database connection to the test main.java.database
     */
    public TestDatabaseHandler() {
        this.properties = new Properties();
        loadPropertiesFile();
        this.conn = loadConnection();
    }

    /**
     * Gets the Properties file.
     * @return properties
     */
    protected Properties getProperties() {
        return properties;
    }

    /**
     * Gets the connection
     * @return connection to test main.java.database
     */
    protected Connection getConnection() {
        return conn;
    }

    /**
     * Load properties from the local file
     */
    private void loadPropertiesFile() {
        try {
            InputStream input = new FileInputStream("/Users/Kolya/Dropbox/Event-Spider/project/src/test.properties");
            properties.load(input);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Uses DatabaseHandler to get a connection to the test main.java.database, using the
     * testing properties file
     * @return Connection to the test main.java.database
     */
    private Connection loadConnection() {
        DatabaseHandler handler = new DatabaseHandler(properties);
        return handler.getConnection();
    }

    /**
     * Main "running" method: calls method to create prepared statement for
     * event inserts, then inserts those events
     */
    public void loadDatabase() {
        Integer rowCounter = 0;
        try {
            ArrayList<PreparedStatement> statements = constructEventInsertStatements();
            for (PreparedStatement statement : statements) {
                rowCounter += statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(rowCounter + " rows inserted.");
    }

    /**
     * Creates an array list prepared statements to insert events into the
     * test main.java.database
     * @return array list of prepared statements
     */
    private ArrayList<PreparedStatement> constructEventInsertStatements()
        throws SQLException {
        ArrayList<PreparedStatement> statementList = new ArrayList<>();
        String sql = properties.getProperty("add.event.sql");
        PreparedStatement statement = conn.prepareStatement(sql);
        for (Map.Entry<String, String> entry : createTitleUrlMapForStatements().entrySet()) {
            statement.setString(1, entry.getKey());
            statement.setString(2, entry.getValue());
            statementList.add(statement);
            statement = conn.prepareStatement(sql);
        }
        return statementList;
    }

    /**
     * Creates a map of Title -> URL for the Insert statement for the
     * test main.java.database
     * @return String, String map main.java.database of Title -> URL
     */
    private Map<String, String> createTitleUrlMapForStatements() {
        Map<String, String> eventMap = new TreeMap<>();
        eventMap.put("Singing In The Rain", "singingintherain.com");
        eventMap.put("1234567", "123456.org");
        eventMap.put("I w8nt 2 G0", "Iw8nt2G0.net/c4mp1ng");
        eventMap.put("!@#$%^&*()", "symbolz.co.uk");
        eventMap.put("Karaoke Singing", "karaoke.singing.com");
        return eventMap;
    }

    /**
     * Deletes all rows in the Events table and prints a message of how many rows were deleted
     * @return number of rows deleted
     */
    public Integer deleteDatabase() {
        Integer rowCounter = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(properties.getProperty("delete.all.sql"));
            rowCounter += statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCounter;
    }


    public static void main(String[] args) throws SQLException {
        TestDatabaseHandler loader = new TestDatabaseHandler();
        loader.loadDatabase();
        loader.deleteDatabase();
    }


}
