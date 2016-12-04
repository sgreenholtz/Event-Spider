package eventspider.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

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
    @GenericGenerator(name="increment", strategy = "identity")
    private int userID;
    @Column(name = "email")
    private String email;
    @Column(name = "pass")
    private String password;
    @Column(name="roles")
    @Enumerated(EnumType.ORDINAL)
    private Roles role;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="UserSavedEvents", joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="event_id"))
    private Set<EventBean> events;



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
        this();
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
     * Gets the value of role.
     *
     * @return role
     */
    public Roles getRole() {
        return role;
    }

    /**
     * Sets role to given value
     *
     * @param role value to set instance variable to
     */
    public void setRole(Roles role) {
        this.role = role;
    }
}