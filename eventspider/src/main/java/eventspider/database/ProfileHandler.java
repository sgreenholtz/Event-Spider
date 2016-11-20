package eventspider.database;

import eventspider.beans.Profile;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Manages operations with the Profile table
 * @author Sebastian Greenholtz
 */
public class ProfileHandler {

    private static final Logger logger = Logger.getLogger(UserHandler.class);
    private Session session;

    /**
     * Empty constructor assigns session var
     */
    public ProfileHandler() {
        this.session = SessionFactoryProvider.getSessionFactory().openSession();
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

}
