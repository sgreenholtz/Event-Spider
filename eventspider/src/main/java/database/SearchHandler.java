package database;

import java.sql.*;
import java.util.*;

/**
 * Performs searches on the database to find events.
 * @author Sebastian Greenholtz
 * @deprecated Use Lucene searcher
 */

public class SearchHandler {

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
        conn = DatabaseHandler.getConnection();
    }

    /**
     * Performs the search on the database
     * @param searchString String from the Keyword field on the search form
     * @return Results Set of the search
     */
    public ResultSet performTitleSearch (String searchString) {
        ResultSet results = null;
        try {
            String sql = createSearchStatementForTitle(splitSearchStringIntoTokens(searchString));
            if (sql == null) {
                return results;
            }
            PreparedStatement statement = conn.prepareStatement(sql);
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
    public ArrayList<String> splitSearchStringIntoTokens(String searchString) {
        ArrayList<String> searchTerms = new ArrayList<>();
        for (String term : searchString.split("\\W")) {
            if (!(term.isEmpty())) {
                searchTerms.add(term);
            }
        }
        return searchTerms;
    }

    /**
     * Creates a SQL statement from the array list of search terms, searching the Title field of the database
     * @param searchTerms Array List of words to search on
     * @return a SQL statement for performing the search
     */
    public String createSearchStatementForTitle(ArrayList<String> searchTerms) {
        String sql = "SELECT * FROM Events WHERE title LIKE ";
        if (searchTerms.size() == 0) {
            return null;
        } else if (searchTerms.size() > 1) {
            for (int i = 0; i < searchTerms.size() - 1; i++) {
                sql += "'%" + searchTerms.get(i) + "%' OR title LIKE ";
            }
        }
        sql+= "'%" + searchTerms.get(searchTerms.size()-1) + "%'";
        return sql;
    }
}