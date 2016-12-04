package eventspider.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Bean to save an event to a user
 * @author Sebastian Greenholtz
 */
@Entity
@Table(name="UserSavedEvents")
public class UserSavedEvents {

    @Id
    @Column(name = "user_event_sk")
    @GenericGenerator(name="eventbean" , strategy="identity")
    @GeneratedValue(generator="eventbean")
    private Integer userEventSk;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="event_id")
    private EventBean event;

    /**
     * Empty constructor
     */
    public UserSavedEvents() {}

    /**
     * Gets the value of userEventSk.
     *
     * @return userEventSk
     */
    public Integer getUserEventSk() {
        return userEventSk;
    }

    /**
     * Sets userEventSk to given value
     *
     * @param userEventSk value to set instance variable to
     */
    public void setUserEventSk(Integer userEventSk) {
        this.userEventSk = userEventSk;
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
     * Gets the value of event.
     *
     * @return event
     */
    public EventBean getEvent() {
        return event;
    }

    /**
     * Sets event to given value
     *
     * @param event value to set instance variable to
     */
    public void setEvent(EventBean event) {
        this.event = event;
    }
}
