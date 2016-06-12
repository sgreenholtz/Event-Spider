package testing;

import org.junit.*;
import org.junit.Assert;

import java.sql.*;
import java.util.Properties;

/**
 * Tests that database was loaded correctly
 * @author Sebastian Greenholtz
 */
public class LoadDatabaseTest {
    private Connection conn;
    private Properties properties;
    private LoadDatabase loadDatabase;

    @org.junit.Before
    public void setUp() throws Exception {
        loadDatabase = new LoadDatabase();
        conn = loadDatabase.getConnection();
        properties = loadDatabase.getProperties();
    }

    @org.junit.Test
    public void loadDatabase() throws SQLException {
        loadDatabase.loadDatabase();
        PreparedStatement statement = conn.prepareStatement(properties.getProperty("select.all.sql"));
        ResultSet results = statement.executeQuery();

        results.next();

    }

    @org.junit.Test
    public void deleteDatabase() throws Exception {

    }

}