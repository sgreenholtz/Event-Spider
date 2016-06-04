package dbOperations;

import java.sql.*;
import java.util.*;

/**
 * Performs searches on the database to find events.
 * @author Sebastian Greenholtz
 */

public class SearchHandler extends DatabaseHandler {

    private Connection conn;

    /**
     * Empty constructor
     */
    public SearchHandler() {

    }

    /**
     * Constructor with properties variable
     * @param properties Application properties
     */
    public SearchHandler(Properties properties) {
        super(properties);
        conn = getConnection();
    }

    /**
     * Parses through the location search to determine what kind of search to perform
     * @param location Search string from the location field
     */
    public void parseLocationSearch(String location) {
//        if (location.matches())
    }
}