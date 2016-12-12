package eventspider.database;

import eventspider.beans.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * Provides methods for handling users: login, registration, verification
 * @author Sebastian Greenholtz
 */

public class UserHandler extends DAO {

    private static final Logger logger = Logger.getLogger(UserHandler.class);

    /**
     * Empty constructor assigns session var
     */
    public UserHandler() {
        super();
    }

    /**
     * Takes a User object, checks if that user is in the database, then
     * validates the password. Returns null if the log in was incorrect.
     * Returns the user object with the password set to NULL if the login
     * is correct
     * @param user User object from log in
     * @return User object if log in is correct, or null if incorrect
     */
    public User logIn(User user) {
        User dbUser = getUserByEmail(user);
        if (!validatePassword(user, dbUser)) {
            return null;
        }
        dbUser.setPassword(null);
        return dbUser;
    }

    /**
     * Gets the User object from the database based on a given email
     * @param user User bean with email
     * @return User bean with all info
     */
    private User getUserByEmail(User user) {
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", user.getEmail()));
        List results = criteria.list();
        if (results.size() == 0) {
            return null;
        }
        return (User) results.get(0);
    }

    /**
     * Compares the password attempt turned into SHA1 with the actual
     * stored value in the database. Returns true if the password is correct
     * @param attempt User object from log in attempt
     * @param actual User object from database
     * @return true if password is correct
     */
    public boolean validatePassword(User attempt, User actual) {
        Boolean valid = false;
        String hexPass = DigestUtils.sha1Hex(attempt.getPassword());
        if (hexPass.equals(actual.getPassword())) {
            valid = true;
        }
        return valid;
    }

    /**
     * Saves user to the database, then creates a user profile for the new user
     * @param reg Registration object to create new user
     */
    public void register(Registration reg) {
        User user = createUser(reg);
        try {
            session.beginTransaction();
            session.save(user);
            User dbUser = getUserByEmail(user);
            Profile profile = createProfile(reg, dbUser.getUserID());
            session.save(profile);
            session.getTransaction().commit();
        } catch (NullPointerException e) {
            logger.error("Error in retreiving new user from database" + e);
        } catch (HibernateException e) {
            logger.error("Something went wrong in adding new user: " + e);
            throw e;
        }
    }

    /**
     * Create the Profile object based on the registration
     * @param reg Registration object
     * @return new Profile object
     */
    private Profile createProfile(Registration reg, int userId) {
        Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setFirstName(reg.getFirstName());
        profile.setLastName(reg.getLastName());
        return profile;
    }

    /**
     * Creates a new User object from the Registration
     * @param reg Registration object
     * @return new User
     */
    private User createUser(Registration reg) {
        User user = new User();
        user.setPassword(DigestUtils.sha1Hex(reg.getPassword()));
        user.setRole(Roles.MEMBER);
        user.setEmail(reg.getEmail());
        return user;
    }

    /**
     * Get user object using the integer Id
     * @param userId Integer userId
     * @return user object for the given user
     */
    public User getUser(Integer userId) {
        return (User)session.get(User.class, userId);
    }



}
