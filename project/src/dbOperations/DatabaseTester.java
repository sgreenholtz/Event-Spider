package dbOperations;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to test database connection
 * @author Sebastian Greenholtz
 */
public class DatabaseTester {

    public static void main(String[] args) throws SQLException {
        String search = "Sample event";
        SearchHandler searcher = new SearchHandler("root", "student", "jdbc:mysql://localhost:3306/eventspider?useSSL=false");
        ResultSet results = searcher.performTitleSearch(search);
        while (results.next()) {
            System.out.println(results.getString("title"));
        }
    }
}
