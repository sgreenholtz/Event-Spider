package database;

import java.sql.*;
import java.util.*;

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
     * Constructor using strings for each variable
     * @param username
     * @param password
     * @param url
     */
    public DatabaseHandler(String username, String password, String url) {
        USERNAME = username;
        PASSWORD = password;
        URL = url;
    }

    /**
     * Sets instance variables based on params
     * @param properties Application properties
     */
    public DatabaseHandler(Properties properties) {
        URL = properties.getProperty("db.url");
        USERNAME = properties.getProperty("db.username");
        PASSWORD = properties.getProperty("db.password");
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

    @Override
    public String toString() {
        return "URL=" + URL + "\n" +
                "USERNAME=" + USERNAME + "\n" +
                "PASSWORD=" + PASSWORD + "\n";
    }
}