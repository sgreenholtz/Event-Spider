package testing;

import database.SearchHandler;
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
    private static SearchHandler searchHandler;
    private static ArrayList<String> searchStringsPhrase;
    private static ArrayList<ArrayList<String>> searchStringsSingleWord;
    private static Map<String, ArrayList<String>> searchStringMap;
    private static ArrayList<String> sqlArrayList;
    private static ArrayList<ArrayList<String>> searchResults;

    @BeforeClass
    public static void setUp() throws SQLException {
        databaseSetUp();
        searchHandler = new SearchHandler(properties);
        loadSearchStringsSingleWord();
        loadSearchStringsPhrase();
        splitSearchStringIntoTokensMap();
        createSQLArrayList();
        getSearchResultSetForTest();
    }

    private static void databaseSetUp() throws SQLException {
        TestDatabaseHandler dbHandler = new TestDatabaseHandler();
        dbHandler.deleteDatabase();
        properties = dbHandler.getProperties();
        dbHandler.loadDatabase();
    }


    private static void loadSearchStringsSingleWord() {
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

    private static void loadSearchStringsPhrase() {
        searchStringsPhrase = new ArrayList<>();
        searchStringsPhrase.add("singing in");
        searchStringsPhrase.add("karaoke, singing");
        searchStringsPhrase.add("1234, G0");
        searchStringsPhrase.add(", .");
    }

    private static void splitSearchStringIntoTokensMap() {
        searchStringMap = new HashMap<>();
        for (String searchPhrase : searchStringsPhrase) {
            searchStringMap.put(searchPhrase, searchHandler.splitSearchStringIntoTokens(searchPhrase));
        }
    }

    private static void createSQLArrayList() {
        sqlArrayList = new ArrayList<>();
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%singing%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%1234%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%1234567%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%sing%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%!@#$%^&*%'");
        sqlArrayList.add("SELECT * FROM Events WHERE title LIKE '%w8nt%'");
    }

    private static void getSearchResultSetForTest() throws SQLException {
        searchResults = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        for (String searchString : searchStringsPhrase) {
            ResultSet results = searchHandler.performTitleSearch(searchString);
            if (results != null) {
                while (results.next()) {
                    titles.add(results.getString("title"));
                }
            }
            searchResults.add(titles);
            titles = new ArrayList<>();
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

    @Test
    public void searchStatementTitleTest() throws SQLException {
        for (Integer i=0; i<6; i++) {
            assertEquals(sqlArrayList.get(i), searchHandler.createSearchStatementForTitle(searchStringsSingleWord.get(i)));
        }
    }

    @Test
    public void performTitleSearchTest() throws SQLException {
        assertTrue(searchResults.get(0).contains("Singing In The Rain"));
        assertTrue(searchResults.get(0).contains("Karaoke Singing"));
        assertTrue(searchResults.get(1).contains("Singing In The Rain"));
        assertTrue(searchResults.get(1).contains("Karaoke Singing"));
        assertTrue(searchResults.get(2).contains("1234567"));
        assertTrue(searchResults.get(2).contains("I w8nt 2 G0"));
        assertEquals(new ArrayList<String>(), searchResults.get(3));
    }



}