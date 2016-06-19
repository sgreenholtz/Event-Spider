package testing;

import dbOperations.AddEvent;
import dbOperations.SearchHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Test the class that adds events to the database
 * @author Sebastian Greenholtz
 */
public class AddEventTest {

    private Properties properties;
    private AddEvent addEvent;

    @Before
    public void setUp() throws Exception {
        TestDatabaseHandler dbHandler = new TestDatabaseHandler();
        dbHandler.deleteDatabase();
        dbHandler.loadDatabase();

        properties = dbHandler.getProperties();
        addEvent = new AddEvent(properties);

    }

    @After
    public void tearDown() throws Exception {
        TestDatabaseHandler dbHandler = new TestDatabaseHandler();
        dbHandler.deleteDatabase();

    }

    @Test
    public void insertEvent() throws Exception {

    }

}