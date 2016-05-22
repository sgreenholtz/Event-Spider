package dbOperations;

import java.sql.*;

/**
 * Provides methods for handling users: login, registration, verification
 * @author Sebastian Greenholtz
 */

public class UserHandler {

    private Connection conn;

    /**
     * Empty constructor. Sets connection based on Database Handler
     */
    public UserHandler() {
        conn = DatabaseHandler.getConnection();
        System.out.println("***** DATABASE CONNECTION TEST: " + conn);
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
            String sql = "SELECT user_id "
                    + "FROM Users "
                    + "WHERE email = ? "
                    + "AND password = SHA1(?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            System.out.println(statement);
            ResultSet results = statement.executeQuery();

            if (!results.isBeforeFirst()) {
                return userID;
            }

            while (results.next()) {
                userID = results.getInt("user_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }

    /**
     * Attempts to add a new user to the database and return the user ID.
     * If there is an error, return -1
     * @param email Email, used as username
     * @param password
     * @param firstName
     * @param lastName
     * @return User ID of new user, or -1 if there is an error
     */
    public Integer register(String email, String password,
                            String firstName, String lastName) {
        Integer userID = -1;
        try {
            String sql = "INSERT INTO Users "
                    + "(email, password, first_name, last_name) "
                    + "VALUES (?, SHA1(?), ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            System.out.println(statement);
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
            e.printStackTrace();
        }
        return userID;
    }

}
