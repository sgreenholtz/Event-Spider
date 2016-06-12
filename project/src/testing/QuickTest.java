package testing;

import java.sql.*;

/**
 * Quick testing in command line
 * @author Sebastian Greenholtz
 */
public class QuickTest {

    public static void main(String[] args) throws SQLException {
        TestDatabaseHandler loader = new TestDatabaseHandler();
        loader.deleteDatabase();
        loader.loadDatabase();
        PreparedStatement statement = loader.getConnection().prepareStatement(loader.getProperties().getProperty("select.all.sql"));
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            System.out.println(results.getString("title") + " " + results.getString("url"));
        }
    }
}
