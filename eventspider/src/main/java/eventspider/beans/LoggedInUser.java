package eventspider.beans;

/**
 * Class representing a logged in user, to be used for operations
 * across the site. Uses only userID and role for validation
 * @author Sebastian Greenholtz
 */
public class LoggedInUser extends User {

    public LoggedInUser() {}

    public LoggedInUser(User user) {
        this.userID = user.userID;
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
}
