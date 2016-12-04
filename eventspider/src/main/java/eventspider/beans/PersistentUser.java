package eventspider.beans;

/**
 * Bean for saving fields used across the application
 * for user verification
 * @author Sebastian Greenholtz
 */
public class PersistentUser {
    private String firstName;
    private Integer userId;
    private Roles role;

    /**
     * Constructor sets the three instance variables
     * @param user User object to save persistent variables
     * @param firstName String first name of user
     */
    public PersistentUser(User user, String firstName) {
        this.firstName = firstName;
        this.userId = user.getUserID();
        this.role = user.getRole();
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
     * Gets the value of userId.
     *
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Gets the value of role.
     *
     * @return role
     */
    public Roles getRole() {
        return role;
    }
}
