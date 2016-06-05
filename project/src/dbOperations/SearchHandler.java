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
     * Constructor using strings for username, password and url, for use in
     * testing only
     * @param username Database username
     * @param password Database password
     * @param url Database url
     */
    public SearchHandler(String username, String password, String url) {
        super(username, password, url);
        conn = getConnection();
    }

    /**
     * Performs the search on the database
     * @param searchString String from the Keyword field on the search form
     * @return Results Set of the search
     */
    public ResultSet performTitleSearch (String searchString) {
        ResultSet results = null;
        PreparedStatement statement = createSearchStatementForTitle(splitSearchStringIntoTokens(searchString));
        try {
            results = statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return results;
    }

    /**
     * Creates an ArrayList of individual words to search the database on
     * @param searchString String from the keyword field of the search form
     * @return Array List of search terms
     */
    private ArrayList<String> splitSearchStringIntoTokens(String searchString) {
        ArrayList<String> searchTerms = new ArrayList<>();
        for (String term : searchString.split("\\W")) {
            if (!(term.isEmpty())) {
                searchTerms.add(term);
            }
        }
        return searchTerms;
    }

    /**
     * Creates a Prepared Statement from the array list of search terms, searching the Title field of the database
     * @param searchTerms Array List of words to search on
     * @return a Prepared Statement for performing the search
     */
    private PreparedStatement createSearchStatementForTitle(ArrayList<String> searchTerms) {
        PreparedStatement statement = null;
        String sql = "SELECT * FROM Events WHERE title LIKE ";
        for (int i=0; i<searchTerms.size()-1; i++) {
            sql += "'%" + searchTerms.get(i) + "%' OR title LIKE ";
        }
        sql+= "'%" + searchTerms.get(searchTerms.size()-1) + "%'";
        try {
            statement = conn.prepareStatement(sql);
//            System.out.println(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return statement;
    }
}