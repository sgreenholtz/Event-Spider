package eventspider.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Represents the UserRoles table
 * @author Sebastian Greenholtz
 */
@Entity
@Table(name="UserRoles")
public class UserRole {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "identity")
    private int roleId;

    protected int userId;

    @Column(name="email")
    protected String email;

    @Column(name="role")
    @Enumerated(EnumType.ORDINAL)
    protected Roles role;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;


    /**
     * Empty constructor
     */
    public UserRole(){}

    /**
     * Constructor setting instance variables
     * @param email
     * @param role
     */
    public UserRole(String email, Roles role) {
        this.email = email;
        this.role = role;
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

    /**
     * Gets the value of user.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user to given value
     *
     * @param user value to set instance variable to
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the value of roleId.
     *
     * @return roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets roleId to given value
     *
     * @param roleId value to set instance variable to
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the value of userId.
     *
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets userId to given value
     *
     * @param userId value to set instance variable to
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
