package eventspider.beans;

import org.hibernate.annotations.Table;

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
    private int userID;
    @Column(name = "email")
    private String email;
    @Column(name = "pass")
    private String pass;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "role")
    private String role;

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
    public String getEmail() {
        return email;
    }

    /**
     * Sets username to given value
     * @param email value to set instance variable to
     */
    public void setEmail(String email) {
        this.email = email;
    }
}