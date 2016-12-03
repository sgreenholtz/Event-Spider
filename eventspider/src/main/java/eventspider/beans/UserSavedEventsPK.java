package eventspider.beans;

import java.io.Serializable;

/**
 * PK for UserSavedEvents class
 * @author Sebastian Greenholtz
 */
public class UserSavedEventsPK implements Serializable {
    protected Integer userID;
    protected Integer eventID;

    /**
     * Empty constructor
     */
    public UserSavedEventsPK(){}

    /**
     * Constructor setting the instance variables
     * @param userID
     * @param eventID
     */
    public UserSavedEventsPK(Integer userID, Integer eventID) {
        this();
        this.userID = userID;
        this.eventID = eventID;
    }
}
