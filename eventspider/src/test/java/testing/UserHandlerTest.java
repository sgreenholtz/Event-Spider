package testing;

import eventspider.beans.LoggedInUser;
import eventspider.beans.User;
import eventspider.database.SessionFactoryProvider;
import eventspider.database.UserHandler;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the UserHandler DAO
 * @author Sebastian Greenholtz
 */
public class UserHandlerTest {
    private static final Logger logger = Logger.getLogger(EventHandlerTest.class);
    //private User user;
    private LoggedInUser loggedInUser;
    private UserHandler handler;
    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();

    @BeforeClass
    public void setUp() {
        logger.info("");
        logger.info("***** STARTING TEST: UserHandlerTest ******");
    }

    @Before
    public void createUsers() throws Exception {
        User user = new User();
        user.setEmail("test@user.com");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setPassword(DigestUtils.sha1Hex("test123"));
        user.setRole("member");

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Test
    public void successfulLogIn() throws Exception {

    }

    @Test
    public void register() throws Exception {

    }

    @Test
    public void getEventsForUser() throws Exception {

    }

}