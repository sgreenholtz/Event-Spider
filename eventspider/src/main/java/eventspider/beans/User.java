package eventspider.beans;

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
    public String getPass() {
        return pass;
    }

    /**
     * Sets pass to given value
     *
     * @param pass value to set instance variable to
     */
    public void setPass(String pass) {
        this.pass = pass;
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

    /**
     * Gets the value of role.
     *
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role to given value
     *
     * @param role value to set instance variable to
     */
    public void setRole(String role) {
        this.role = role;
    }
}