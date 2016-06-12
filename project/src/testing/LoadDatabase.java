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
     */
    public LoadDatabase() {
        this.properties = new Properties();
        try {
            InputStream input = new FileInputStream("project/src/test.properties");
            properties.load(input);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.conn = loadConnection();
    }

    /**
     * Uses DatabaseHandler to get a connection to the test database, using the
     * testing properties file
     * @return Connection to the test database
     */
    private Connection loadConnection() {
        DatabaseHandler handler = new DatabaseHandler(properties);
        Connection conn = handler.getConnection();
        return conn;
    }


    public static void main(String[] args) {
        LoadDatabase loader = new LoadDatabase();

    }


}
