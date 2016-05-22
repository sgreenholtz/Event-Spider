package dbOperations;

import java.sql.*;

/**
 * Handles communication with the database. Gets environment variables from
 * Openshift and starts a connection
 * @author Sebastian Greenholtz
 */
public class DatabaseHandler {

    private static String USERNAME;
    private static String PASSWORD;
    private static String URL;

    /**
     * Sets the four environment variables based on OpenShift environment
     */
    public DatabaseHandler() {
//        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
//        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        USERNAME = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
//        URL = String.format("jdbc:mysql://%s:%s/eventspider", host, port);
        URL = System.getenv("OPENSHIFT_MYSQL_DB_URL");
        System.out.println("****** URL: " + URL + " *******");
    }

    /**
     * Uses system variables to get a connection with the database
     * @return Database connection
     */
    public static Connection getConnection() {
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
