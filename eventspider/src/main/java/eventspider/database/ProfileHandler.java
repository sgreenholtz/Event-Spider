package eventspider.database;

import eventspider.beans.Profile;
import eventspider.beans.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Manages operations with the Profile table
 * @author Sebastian Greenholtz
 */
public class ProfileHandler extends DAO{

    private static final Logger logger = Logger.getLogger(UserHandler.class);

    /**
     * Empty constructor assigns session var
     */
    public ProfileHandler() {
        super();
    }

    /**
     * Gets the Profile object for the given user ID
     * @param userId ID of the user to retrieve their profile
     * @return Profile object
     */
    public Profile getProfile(Integer userId) {
        return (Profile) session.get(Profile.class, userId);
    }

    /**
     * Updates the Profile record based on the object
     * @param profile Profile object
     * @return true if update is successful
     */
    public boolean updateProfile(Profile profile) {
        try {
            session.beginTransaction();
            Profile tblProfile = (Profile) session.get(Profile.class, profile.getUserId());
            tblProfile.setFirstName(profile.getFirstName());
            tblProfile.setLastName(profile.getLastName());
            tblProfile.setImage(profile.getImage());
            logger.info("Updated " + profile.getUserId());
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * For a given userId, gets the first name of the user
     * @param userId Integer id for user
     * @return String of the user's first name
     */
    public String getFirstNameForUser(Integer userId) {
        try {
            String sql = "Select firstName from Profile where userId=" + userId;
            return (String) session.createQuery(sql).list().get(0);
        } catch (Exception e) {
            logger.error(e);
        }
        return "";
    }

    /**
     * Constructs the Profile object to populate the Profile page
     * @param user User object of the user to get their profile
     * @return Profile object for the given user
     */
    public Profile getProfile(User user) {
        ProfileHandler profileHandler = new ProfileHandler();
        Profile profile = profileHandler.getProfile(user.getUserID());
        profile.setEvents(user.getEvents());
        logger.info("Retrieved user: " + profile.getUserId());
        return profile;
    }

}
