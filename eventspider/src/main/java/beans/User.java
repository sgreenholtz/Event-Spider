package beans;

/**
 * Represents a user
 * @author Sebastian Greenholtz
 */
public class User {

    private int userID;
    private String username;

    /**
     * Gets the value of userID.
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets userID to given value
     * @param userID value to set instance variable to
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the value of username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username to given value
     * @param username value to set instance variable to
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
