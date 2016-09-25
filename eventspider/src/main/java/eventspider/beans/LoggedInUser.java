package eventspider.beans;

/**
 * Class representing a logged in user, to be used for operations
 * across the site. Key difference is password is not available from this class.
 * @author Sebastian Greenholtz
 */
public class LoggedInUser extends User {

    public LoggedInUser() {}

    public LoggedInUser(User user) {
        this.userID = user.userID;
        this.email = user.email;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.role = user.role;
    }

    /**
     * Overrides inherited equals method for LoggedInUser object.
     * @param user LoggedInUser object to compare
     * @return True if all instance vars are the same
     */
    public boolean equals(LoggedInUser user) {
        return ((this.userID == user.userID) &&
                (this.email.equals(user.email)) &&
                (this.firstName.equals(user.firstName)) &&
                (this.lastName.equals(user.lastName)) &&
                (this.role.equals(user.role)));
    }
}
