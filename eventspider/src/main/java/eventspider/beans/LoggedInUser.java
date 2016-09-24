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
}
