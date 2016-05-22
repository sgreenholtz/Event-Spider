package dbOperations;

import java.sql.*;

/**
 * Handles communication with the database. Takes in the database variables and
 * returns a connection with the database, to be used by other classes that communicate
 * with the database. Intended to streamline database communication but not
 * duplicating the connection code.
 * @author Sebastian Greenholtz
 */
public class DatabaseHandler {

    private static String USERNAME;
    private static String PASSWORD;
    private static String URL;

    /**
     * Empty constructor
     */
    public DatabaseHandler() {}

    /**
     * Sets instance variables based on params
     * @param url URL of the database
     * @param username username for the database
     * @param password password for the database
     */
    public DatabaseHandler(String url, String username, String password) {
        URL = url;
        USERNAME = username;
        PASSWORD = password;
    }

    /**
     * Uses system variables to get a connection with the database
     * @return Database connection
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return conn;
    }
}