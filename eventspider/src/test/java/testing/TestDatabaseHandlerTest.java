package testing;

import org.junit.*;

import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Tests that main.java.database was loaded correctly
 * @author Sebastian Greenholtz
 */
public class TestDatabaseHandlerTest {
    private static Properties properties;
    private static TestDatabaseHandler testDatabaseHandler;
    private ResultSet insertResults;

    @BeforeClass
    public static void setUp() throws SQLException {
        testDatabaseHandler = new TestDatabaseHandler();
        properties = testDatabaseHandler.getProperties();
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        testDatabaseHandler.getConnection().close();
    }

    @Before
    public void getResultSet() throws SQLException {
        testDatabaseHandler.deleteDatabase();
        testDatabaseHandler.loadDatabase();
        PreparedStatement statement = testDatabaseHandler.getConnection().prepareStatement(properties.getProperty("select.all.sql"));
        insertResults = statement.executeQuery();
    }

    @Test
    public void firstRowLoaded() throws SQLException {
        insertResults.absolute(1);
        assertEquals(insertResults.getString("title"), "!@#$%^&*()");
        assertEquals(insertResults.getString("url"), "symbolz.co.uk");
    }

    @Test
    public void secondRowLoaded() throws SQLException {
        insertResults.absolute(2);
        assertEquals(insertResults.getString("title"), "1234567");
        assertEquals(insertResults.getString("url"), "123456.org");
    }

    @Test
    public void thirdRowLoaded() throws SQLException {
        insertResults.absolute(3);
        assertEquals(insertResults.getString("title"), "I w8nt 2 G0");
        assertEquals(insertResults.getString("url"), "Iw8nt2G0.net/c4mp1ng");
    }

    @Test
    public void forthRowLoaded() throws SQLException {
        insertResults.absolute(4);
        assertEquals(insertResults.getString("title"), "Singing In The Rain");
        assertEquals(insertResults.getString("url"), "singingintherain.com");
    }

    @After
    public void deleteDatabaseRows() throws SQLException {
        insertResults.close();
        assertEquals("Rows deleted", testDatabaseHandler.deleteDatabase(), new Integer(4));
    }

}