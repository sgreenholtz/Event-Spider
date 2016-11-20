package eventspider.beans;

import eventspider.database.EventHandler;
import eventspider.database.UserHandler;

/**
 * Contructs factory beans
 * @author Sebastian Greenholtz
 * TODO: finish this class
 */
public class ProfileFactory {

    private int userId;

    /**
     * Constructor sets userId
     * @param id
     */
    public ProfileFactory(int id) {
        userId = id;
    }

    public Profile getProfile() {
        Profile profile = new Profile();
        //get the path to the image
        UserHandler handler = new UserHandler();
        profile.setEvents(handler.getEventsForUser(userId));

        return profile;
    }
}
