package eventspider.database;

import eventspider.beans.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import eventspider.beans.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * Provides methods for handling users: login, registration, verification
 * @author Sebastian Greenholtz
 */

public class UserHandler {

    public static Integer EMAIL_DOESNT_EXIST = 0;
    public static Integer PASSWORD_INCORRECT = 1;

    private static final Logger logger = Logger.getLogger(UserHandler.class);
    private Session session;

    /**
     * Empty constructor assigns session var
     */
    public UserHandler() {
        this.session = SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Takes a User object, checks if that user is in the database, then
     * validates the password. Returns null if the log in was incorrect
     * @param user User object from log in
     * @return LoggedInUser object if log in is correct, or null if incorrect
     */
    public LoggedInUser logIn(User user) {
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
        return new LoggedInUser(dbUser);
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
     */
    public void register(User user) {
        user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        session.beginTransaction();
        session.save(user);
        logger.info("Event added: " + user.getEmail());
        session.getTransaction().commit();
    }

    /**
     * Gets a Result Set of all the events saved for a given user
     * @param user LoggedInUser to get events for
     * @return Map of EventID->Bean for the user
     */
    public List<EventBean> getEventsForUser(LoggedInUser user) {
        String sql = "from Events where UserSavedEvents.userID=" + user.getUserID();
        List<EventBean> list = session.createQuery(sql).list();
        return list;
    }

}
