package testing;

import eventspider.factories.EventFactory;
import eventspider.beans.Profile;
import eventspider.database.EventHandler;
import eventspider.database.ProfileHandler;
import eventspider.database.SessionFactoryProvider;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ProfileHandler class
 * @author Sebastian Greenholtz
 */
public class ProfileHandlerTest {

    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private EventHandler handler = new EventHandler();
    private static EventFactory factory = new EventFactory();
    private static final Logger logger = Logger.getLogger(EventHandlerTest.class);

    @BeforeClass
    public static void setUp() throws Exception {
        logger.info("");
        logger.info("***** STARTING TEST: ProfileHandler ******");
    }

    @Before
    public void addEventsToDB() throws Exception {
        session.clear();
        clearDatabase();
        Profile profile = new Profile();
        profile.setUserId(1);
        profile.setFirstName("Test");
        profile.setLastName("User");
        session.beginTransaction();
        session.save(profile);
        session.getTransaction().commit();
    }

    private void clearDatabase() {
        String sql = "DELETE FROM Profile";
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        session.close();
    }

    @Test
    public void getProfileTest() throws Exception {
        ProfileHandler handler = new ProfileHandler();
        Profile actual = handler.getProfile(1);
        assertTrue("First name does not match", actual.getFirstName().equals("Test"));
        assertTrue("Last name does not match", actual.getLastName().equals("User"));
        assertTrue("User ID does not match", actual.getUserId().equals(1));
    }


}