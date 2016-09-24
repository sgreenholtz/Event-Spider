package eventspider.beans;

/**
 * Represents a user
 * @author Sebastian Greenholtz
 */
public class User {

    private int userID;
    private String email;
    private String password;

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
     * Gets the value of email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email to given value
     *
     * @param email value to set instance variable to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the value of password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password to given value
     *
     * @param password value to set instance variable to
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
