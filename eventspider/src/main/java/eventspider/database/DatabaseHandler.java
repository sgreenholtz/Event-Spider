package eventspider.database;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Singleton to handle communication with the database. Takes in the database variables and
 * returns a connection with the database, to be used by other classes that communicate
 * with the database. Intended to streamline database communication but not
 * duplicating the connection code.
 * @author Sebastian Greenholtz
 */
@Deprecated
public class DatabaseHandler {

    private static String USERNAME;
    private static String PASSWORD;
    private static String URL;
    private static Connection connection;
    private static DatabaseHandler instance = new DatabaseHandler();
    private static final Logger logger = Logger.getLogger(DatabaseHandler.class);

    /**
     * Constructor using strings for each variable
     * @param username
     * @param password
     * @param url
     */
    private DatabaseHandler(String username, String password, String url) {
        USERNAME = username;
        PASSWORD = password;
        URL = url;
    }

    /**
     * Sets instance variables based on params
     */
    private DatabaseHandler() {
        Properties properties = null;
        URL = properties.getProperty("db.url");
        USERNAME = properties.getProperty("db.username");
        PASSWORD = properties.getProperty("db.password");
    }

    /**
     * Uses system variables to get a connection with the database
     * @return Database connection
     */
    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException ex) {
                logger.error(ex.getStackTrace());
            } catch (SQLException sql) {
                logger.error(sql.getStackTrace());
            }
            return connection;
        }
    }

    public static DatabaseHandler getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "URL=" + URL + "\n" +
                "USERNAME=" + USERNAME + "\n" +
                "PASSWORD=" + PASSWORD + "\n";
    }
}