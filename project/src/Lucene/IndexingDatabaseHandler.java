package Lucene;

import java.sql.*;
import java.util.*;
import dbOperations.DatabaseHandler;

/**
 * Handles communications with the database to get the data to index
 * @author Sebastian Greenholtz
 */
public class IndexingDatabaseHandler {

    private Connection conn;

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
        DatabaseHandler db = new DatabaseHandler(properties);
        conn = db.getConnection();
    }

}
