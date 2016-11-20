package eventspider.beans;

import javax.persistence.*;
import java.util.List;

/**
 * Represents all the data for a user profile
 * @author Sebastian Greenholtz
 */
@Entity
@Table(name="Profile")
public class Profile {

    @Id
    @Column(name="user_id")
    private Integer userId;

    @Column(name="photo_path")
    private String image;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Transient
    private List<EventBean> events;

    /**
     * Gets the value of image.
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image to given value
     * @param image value to set instance variable to
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the value of events.
     * @return events
     */
    public List<EventBean> getEvents() {
        return events;
    }

    /**
     * Sets events to given value
     * @param events value to set instance variable to
     */
    public void setEvents(List<EventBean> events) {
        this.events = events;
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
     * Sets userId to given value
     *
     * @param userId value to set instance variable to
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
