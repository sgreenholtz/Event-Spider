package eventspider.beans;

import javax.persistence.*;

/**
 * Bean to save an event to a user
 * @author Sebastian Greenholtz
 */
@Entity
@Table(name="UserSavedEvents")
@IdClass(UserSavedEventsPK.class)
public class UserSavedEvents {
    @Id
    @Column(name="user_id")
    private Integer userID;

    @Id
    @Column(name="event_id")
    private Integer eventID;

    /**
     * Empty constructor
     */
    public UserSavedEvents() {}

    /**
     * Gets the value of userID.
     *
     * @return userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Sets userID to given value
     *
     * @param userID value to set instance variable to
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Gets the value of eventID.
     *
     * @return eventID
     */
    public Integer getEventID() {
        return eventID;
    }

    /**
     * Sets eventID to given value
     *
     * @param eventID value to set instance variable to
     */
    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }
}
