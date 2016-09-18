package eventspider.beans;

import java.io.Serializable;

/**
 * PK for UserSavedEvents class
 * @author Sebastian Greenholtz
 */
public class UserSavedEventsPK implements Serializable {
    protected Integer userID;
    protected Integer eventID;

    public UserSavedEventsPK(){}

    public UserSavedEventsPK(Integer userID, Integer eventID) {
        this.userID = userID;
        this.eventID = eventID;
    }
}
