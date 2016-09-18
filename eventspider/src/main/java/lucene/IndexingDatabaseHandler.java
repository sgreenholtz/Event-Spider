package lucene;

import java.sql.*;
import java.util.*;
import database.DatabaseHandler;

/**
 * Handles communications with the database to get the data to index
 * @author Sebastian Greenholtz
 */
public class IndexingDatabaseHandler {

    private Connection conn;
    private Properties properties;

    /**
     * Empty constructor
     */
    public IndexingDatabaseHandler() {}

    /**
     * Creates a new IndexingDatabaseHandler using the application
     * properties file so DatabaseHandler can get the connection to the
     * database
     * @param properties Application properties file
     */
    public IndexingDatabaseHandler(Properties properties) {
        conn = DatabaseHandler.getConnection();
        this.properties = properties;
    }

    /**
     * Selects everything from the Events table in order to index it.
     * @return ResultSet of all data in the Events table
     */
    public ResultSet getResults() {
        ResultSet selectResults = null;
        try {
            String sql = properties.getProperty("lucene.select.all.sql");
            PreparedStatement statement = conn.prepareStatement(sql);
            selectResults = statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return selectResults;
    }

}
