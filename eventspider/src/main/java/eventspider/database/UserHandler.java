package eventspider.database;

import eventspider.beans.*;
import org.apache.log4j.Logger;
import eventspider.beans.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Provides methods for handling users: login, registration, verification
 * @author Sebastian Greenholtz
 */

public class UserHandler {

    private Connection conn;
    private Properties properties;
    private static final Logger logger = Logger.getLogger(UserHandler.class);
    private Session session;

    /**
     * Empty constructor.
     */
    public UserHandler() {
        this.session = SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Constructor that takes in url, username and password and sets up the
     * connection with the database using the database handler
     * @param properties Application properties
     */
    public UserHandler(Properties properties) {
        this();
        this.properties = properties;
        conn = DatabaseHandler.getConnection();
    }

    /**
     * Attempts to log in a user. Returns user id if the user is logged in, returns
     * -1 if the user was not found.
     * @param email Email to identify the user
     * @param password Password to attempt to log in user
     * @return The user id of the logged in user, or -1 if no user was found
     */
    public Integer logIn(String email, String password) {
        Integer userID = -1;
        try {
            String sql = properties.getProperty("log.in.sql");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet results = statement.executeQuery();

            if (!results.isBeforeFirst()) {
                return userID;
            }

            while (results.next()) {
                userID = results.getInt("user_id");
            }

        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return userID;
    }

    /**
     * Takes a User object, checks if that user is in the database, then
     * validates the password. Returns null if the log in was incorrect
     * @param user User object from log in
     * @return LoggedInUser object if log in is correct, or null if incorrect
     */
    public LoggedInUser logIn(User user) {
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", user.getEmail()));
        List results = criteria.list();
        if (results.size() == 0) {
            return null;
        }

        User dbUser = (User) results.get(0);
        if (!validatePassword(user, dbUser)) {
            return null;
        }
        return new LoggedInUser(dbUser);
    }

    public boolean validatePassword(User attempt, User actual) {
        Boolean valid = false;

        return valid;
    }

    /**
     * Attempts to add a new user to the database and return the user ID.
     * If there is an error, return -1
     * @param email Email, used as username
     * @param password user's password
     * @param firstName user's first name
     * @param lastName user's last name
     * @return User ID of new user, or -1 if there is an error
     */
    public Integer register(String email, String password,
                            String firstName, String lastName) {
        Integer userID = -1;
        try {
            String sql = properties.getProperty("register.sql");

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            logger.info(statement);
            statement.executeUpdate();

            String lastID = "SELECT LAST_INSERT_ID();";
            statement = conn.prepareStatement(lastID);
            ResultSet idResult = statement.executeQuery();

            if (!idResult.isBeforeFirst()) {
                return userID;
            }

            while (idResult.next()) {
                userID = idResult.getInt("user_id");
            }

        } catch (SQLException e) {
            logger.error(e.getStackTrace());
        }
        return userID;
    }

    /**
     * Gets a Result Set of all the events saved for a given user
     * @param userID User to get events for
     * @return result set of all the events saved to a given user
     */
    public ResultSet getEventsForUser(Integer userID) {
        ResultSet results = null;
        try {
            String sql = properties.getProperty("get.user.events");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userID);
            results = statement.executeQuery();
        } catch (SQLException ex) {
            logger.error(ex.getStackTrace());
        }
        return results;
    }

}
