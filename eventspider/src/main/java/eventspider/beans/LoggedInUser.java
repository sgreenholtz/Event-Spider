package eventspider.beans;

/**
 * Class representing a logged in user, to be used for operations
 * across the site. Uses only userID and role for validation
 * @author Sebastian Greenholtz
 */
public class LoggedInUser extends User {

    private String firstName;

    public LoggedInUser() {}

    /**
     * Constructor sets id, role and first name for usage throughout the application
     * @param user User object
     * @param firstName first name, string
     */
    public LoggedInUser(User user, String firstName) {
        this.userID = user.userID;
        this.firstName = firstName;
        this.role = user.role;
    }

    /**
     * Overrides inherited equals method for LoggedInUser object.
     * @param user LoggedInUser object to compare
     * @return True if all instance vars are the same
     */
    public boolean equals(LoggedInUser user) {
        return ((this.userID == user.userID) &&
                (this.role.equals(user.role)));
    }

    /**
     * Gets the value of firstName.
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName to given value
     *
     * @param firstName value to set instance variable to
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
