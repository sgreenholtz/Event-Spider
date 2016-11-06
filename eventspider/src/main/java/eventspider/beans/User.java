package eventspider.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Represents a user
 * @author Sebastian Greenholtz
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    protected int userID;
    @Column(name = "email")
    protected String email;
    @Column(name = "pass")
    private String password;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;

    /**
     * Empty constructor
     */
    public User() {}

    /**
     * Constructor with email and password
     * @param email String email
     * @param password String password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the value of userID.
     *
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets userID to given value
     *
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
     * Gets the value of pass.
     *
     * @return pass
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets pass to given value
     *
     * @param pass value to set instance variable to
     */
    public void setPassword(String pass) {
        this.password = pass;
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

    /**
     * Gets the value of lastName.
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName to given value
     *
     * @param lastName value to set instance variable to
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}