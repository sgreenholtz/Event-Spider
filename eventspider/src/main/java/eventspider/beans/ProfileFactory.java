package eventspider.beans;

import eventspider.database.ProfileHandler;
import eventspider.database.UserHandler;
import org.apache.log4j.Logger;

/**
 * Constructs profile bean
 * @author Sebastian Greenholtz
 * TODO: finish this class
 */
public class ProfileFactory {

    private static final Logger logger = Logger.getLogger(ProfileFactory.class);

    /**
     * Constructs the Profile object to populate the Profile page
     * @param userId ID of the user to get their profile
     * @return Profile object for the given user
     */
    public Profile getProfile(Integer userId) {
        ProfileHandler profileHandler = new ProfileHandler();
        Profile profile = profileHandler.getProfile(userId);
        UserHandler userHandler = new UserHandler();
        profile.setEvents(userHandler.getEventsForUser(userId));
        logger.info("Retrieved user: " + profile.getUserId());
        return profile;
    }
}
