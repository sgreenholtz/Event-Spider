package eventspider.beans;

/**
 * Bean to assist with registration of a new user
 * @author Sebastian Greenholtz
 */
public class Registration {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

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
