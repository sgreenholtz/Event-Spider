package eventspider.database;

import eventspider.beans.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import eventspider.beans.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", user.getEmail()));
        List results = criteria.list();
        if (results.size() == 0) {
            return null;
        }

        User dbUser = (User) results.get(0);
        if (!validatePassword(user, dbUser)) {
            return null;
        }
        dbUser.setPassword(null);
        return dbUser;
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
        if (DigestUtils.sha1Hex(attempt.getPassword()).equals(actual.getPassword())) {
            valid = true;
        }
        return valid;
    }

    /**
     * Registers new user
     * @param user User object to add to database
     * TODO: fix this so that Profile is set up for new user
     */
    public void register(User user) throws RequiredFieldMissingException {
        if (requiredEmailNull(user)) {
            throw new RequiredFieldMissingException("Email is a required field");
        } else if (requiredPasswordNull(user)) {
            throw new RequiredFieldMissingException("Password is a required field");
        } else {
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            user.setRole(Roles.MEMBER);
            try {
                session.beginTransaction();
                session.save(user);
                logger.info("Event added: " + user.getEmail());
                session.getTransaction().commit();
            } catch (HibernateException e) {
                logger.error("Something went wrong in adding new user: " + e);
                throw e;
            }
        }
    }

    public boolean requiredEmailNull(User user) {
        return (user.getEmail() == null);
    }

    public boolean requiredPasswordNull(User user) {
        return (user.getPassword() == null);
    }

}
