package testing;

import dbOperations.SearchHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests the search function in the database
 * @author Sebastian Greenholtz
 */
public class SearchHandlerTest {

    private static Properties properties;
    private static Connection conn;
    private static SearchHandler searchHandler;
    private static ArrayList<String> searchStringsPhrase;
    private static ArrayList<ArrayList<String>> searchStringsSingleWord;
    private Map<String, ArrayList<String>> searchStringMap;
    private ArrayList<String> sqlArrayList;
    private ArrayList<ResultSet> searchResults;
    private Map<String, ArrayList<String>> searchResultsMap;

    @BeforeClass
    public static void setUp() throws SQLException {
        TestDatabaseHandler dbHandler = new TestDatabaseHandler();
        dbHandler.deleteDatabase();
        properties = dbHandler.getProperties();
        conn = dbHandler.getConnection();
        dbHandler.loadDatabase();
        searchHandler = new SearchHandler(properties);
        searchStringsPhrase = new ArrayList<>();
    }

    @BeforeClass
    public static void loadSearchStringsSingleWord() {
        ArrayList<String> term = new ArrayList<>();
        searchStringsSingleWord = new ArrayList<ArrayList<String>>();

        term.add("singing");
        searchStringsSingleWord.add(term);
        term = new ArrayList<>();

        term.add("1234");
        searchStringsSingleWord.add(term);
        term = new ArrayList<>();

        term.add("1234567");
        searchStringsSingleWord.add(term);
        term = new ArrayList<>();

        term.add("sing");
        searchStringsSingleWord.add(term);
        term = new ArrayList<>();

        term.add("!@#$%^&*");
        searchStringsSingleWord.add(term);
        term = new ArrayList<>();

        term.add("w8nt");
        searchStringsSingleWord.add(term);
    }

    @BeforeClass
    public static void loadSearchStringsPhrase() {
        searchStringsPhrase.add("singing in");
        searchStringsPhrase.add("karaoke, singing");
        searchStringsPhrase.add("1234, G0");
        searchStringsPhrase.add(", .");
    }

    @Before
    public void splitSearchStringIntoTokensMap() {
        searchStringMap = new HashMap<>();
        for (String searchPhrase : searchStringsPhrase) {
            searchStringMap.put(searchPhrase, searchHandler.splitSearchStringIntoTokens(searchPhrase));
        }
    }

    @Test
    public void splitSearchStringIntoTokensTest() {
        ArrayList<String> resultArrayList = new ArrayList<>();

        resultArrayList.add("singing");
        resultArrayList.add("in");
        assertEquals("For 'singing in':", searchStringMap.get("singing in"), resultArrayList);
        resultArrayList.clear();

        resultArrayList.add("karaoke");
        resultArrayList.add("singing");
        assertEquals("For 'karaoke, singing':", searchStringMap.get("karaoke, singing"), resultArrayList);
        resultArrayList.clear();

        resultArrayList.add("1234");
        resultArrayList.add("G0");
        assertEquals("For '1234, G0':", searchStringMap.get("1234, G0"), resultArrayList);
        resultArrayList.clear();

        assertEquals("For ', .':", searchStringMap.get(", ."), resultArrayList);
    }

    @Before
    public void createSQLArrayList() {
        sqlArrayList = new ArrayList<>();
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%singing%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%1234%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%1234567%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%sing%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%!@#$%^&*%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%w8nt%'");
    }

    @Test
    public void searchStatementTitleTest() throws SQLException {
        for (Integer i=0; i<6; i++) {
            assertEquals(sqlArrayList.get(i), searchHandler.createSearchStatementForTitle(searchStringsSingleWord.get(i)));
        }
    }

    @Before
    public void getSearchResultSetForTest() {
        searchResults = new ArrayList<>();
        for (String searchString : searchStringsPhrase) {
            searchHandler.performTitleSearch(searchString);
        }
    }

    @Before
    public void searchResultsExpected() {
        searchResultsMap = new HashMap<>();
        ArrayList<String> searchResultEventTitle = new ArrayList<>();

        searchResultEventTitle.add("Singing In The Rain");
        searchResultEventTitle.add("Karaoke Singing");
        searchResultsMap.put("singing in", searchResultEventTitle);
        searchResultEventTitle = new ArrayList<>();

        searchResultEventTitle.add("Singing In The Rain");
        searchResultEventTitle.add("Karaoke Singing");
        searchResultsMap.put("karaoke, singing", searchResultEventTitle);
        searchResultEventTitle = new ArrayList<>();

        searchResultEventTitle.add("1234567");
        searchResultEventTitle.add("I w8nt 2 G0");
        searchResultsMap.put("1234, G0", searchResultEventTitle);
        searchResultEventTitle = new ArrayList<>();
    }

    @Test
    public void performTitleSearchTest() throws SQLException {
//        searchResults.get(0).absolute(1);
//        assertTrue()
//        assertEquals(searchResultsMap.get("singing in").get(1), searchResults.get(0).getString("title"));
//        searchResults.get(0).absolute(2);
//        assertEquals(searchResultsMap.get("singing in").get(0), searchResults.get(0).getString("title"));
//
//        searchResults.get(1).absolute(1);
//        assertEquals(searchResultsMap.get("karaoke, singing").get(0), searchResults.get(1).getString("title"));
//        searchResults.get(1).absolute(2);
//        assertEquals(searchResultsMap.get("karaoke, singing").get(1), searchResults.get(1).getString("title"));
//
//        searchResults.get(2).absolute(1);
//        assertEquals(searchResultsMap.get("1234, G0").get(0), searchResults.get(2).getString("title"));
//        searchResults.get(2).absolute(2);
//        assertEquals(searchResultsMap.get("1234, G0").get(1), searchResults.get(2).getString("title"));

        searchResults.get(3);
        assertEquals(null, searchResults.get(3));
    }



}