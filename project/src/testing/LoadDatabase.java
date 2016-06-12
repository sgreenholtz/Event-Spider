package testing;

import dbOperations.DatabaseHandler;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Loads up the database for use in testing
 * @author Sebastian Greenholtz
 */
public class LoadDatabase {

    private Properties properties;
    private Connection conn;

    /**
     * Constructor loads up the properties file for testing
     * and gets a database connection to the test database
     */
    public LoadDatabase() {
        this.properties = new Properties();
        loadPropertiesFile();
        this.conn = loadConnection();
    }

    /**
     * Load properties from the local file
     */
    private void loadPropertiesFile() {
        try {
            InputStream input = new FileInputStream("project/src/test.properties");
            properties.load(input);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Uses DatabaseHandler to get a connection to the test database, using the
     * testing properties file
     * @return Connection to the test database
     */
    private Connection loadConnection() {
        DatabaseHandler handler = new DatabaseHandler(properties);
        return handler.getConnection();
    }

    /**
     * Creates an array list of 10 prepared statements to insert 10 events into the
     * test database
     * @return array list of prepared statements
     */
    public ArrayList<PreparedStatement> constructEventInsertStatements()
        throws SQLException {
        ArrayList<PreparedStatement> statementList = new ArrayList<>();
        String sql = properties.getProperty("add.event.sql");
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "Singing in the Rain");
        statement.setString(2, "www.singingintherain.com");

        return statementList;
    }

    private Map<String, String> createTitleUrlMapForStatements() {
        Map<String, String> eventMap = new HashMap<>();

        return eventMap;
    }


    public static void main(String[] args) {
        LoadDatabase loader = new LoadDatabase();

    }


}
