package testing;

import eventspider.beans.LoggedInUser;
import eventspider.beans.RequiredFieldMissingException;
import eventspider.beans.User;
import eventspider.database.SessionFactoryProvider;
import eventspider.database.UserHandler;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the UserHandler DAO
 * @author Sebastian Greenholtz
 */
public class UserHandlerTest {
    private static final Logger logger = Logger.getLogger(EventHandlerTest.class);
    private static User userInDB;
    private static LoggedInUser loggedInUser;
    private UserHandler handler = new UserHandler();
    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();

    @BeforeClass
    public static void setUp() {
        logger.info("");
        logger.info("***** STARTING TEST: UserHandlerTest ******");
        clearDatabase();
        addUserToTestDB();
        loggedInUser = createLoggedInUser();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        session.close();
    }

    private static LoggedInUser createLoggedInUser() {
        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setUserID(1);
        loggedInUser.setEmail("test@user.com");
        loggedInUser.setFirstName("Test");
        loggedInUser.setLastName("User");
        loggedInUser.setRole("member");
        return loggedInUser;
    }

    private static void addUserToTestDB() {
        User user = new User();
        user.setEmail("test@user.com");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setPassword(DigestUtils.sha1Hex("test123"));
        user.setRole("member");

        userInDB = user;

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public static void clearDatabase() {
        String sql = "DELETE FROM Users";
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void passwordMatchTest() throws Exception {
        User logInAttempt = new User("test@user.com", "test123");
        assertTrue("Passwords did not match", handler.validatePassword(logInAttempt, userInDB));
    }

    @Test
    public void passwordMismatchTest() throws Exception {
        User logInAttempt = new User("test@user.com", "test321");
        assertFalse("Password check failed", handler.validatePassword(logInAttempt, userInDB));
    }

    /**
     * Uses overridden equals method rather than assertEquals to compare the
     * fields of loggedInUser rather than the object hash
     */
    @Test
    public void logInSuccessful() throws Exception {
        User logInAttempt = new User("test@user.com", "test123");
        LoggedInUser actual = handler.logIn(logInAttempt);
        assertTrue("Log in failed", loggedInUser.equals(actual));
    }

    @Test
    public void logInFailEmailDoesntExist() throws Exception {
        User logInAttempt = new User("test@fail.com", "test123");
        assertNull("Log in verification failed", handler.logIn(logInAttempt));
    }

    @Test
    public void logInFailPasswordIncorrect() throws Exception {
        User logInAttempt = new User("test@user.com", "test321");
        assertNull("Log in verification failed", handler.logIn(logInAttempt));
    }

    @Test(expected = RequiredFieldMissingException.class)
    public void registerFailNoEmail() throws Exception {
        User emptyUser = new User();
        emptyUser.setPassword("test123");
        handler.register(emptyUser);
    }

    @Test(expected = RequiredFieldMissingException.class)
    public void registerFailNoPassword() throws Exception {
        User emptyUser = new User();
        emptyUser.setEmail("test@user.com");
        handler.register(emptyUser);
    }

    @Test
    public void registerSuccess() throws Exception {
        User user = new User();
        user.setEmail("test2@user.com");
        user.setFirstName("Test2");
        user.setLastName("User2");
        user.setPassword(DigestUtils.sha1Hex("test123"));
        user.setRole("member");

        handler.register(user);

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", "test2@user.com"));
        List results = criteria.list();
        assertEquals("User was not added correctly", 1, results.size());
    }

}