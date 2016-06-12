package testing;

import dbOperations.SearchHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Tests the search function in the database
 * @author Sebastian Greenholtz
 */
public class SearchHandlerTest {

    private static Properties properties;
    private static SearchHandler searchHandler;
    private static ArrayList<String> searchStringsPhrase;
    private static ArrayList<String> searchStringsSingleWord;
    private Map<String, ArrayList<String>> searchStringMap;

    @BeforeClass
    public static void setUp() throws SQLException {
        TestDatabaseHandler dbHandler = new TestDatabaseHandler();
        dbHandler.deleteDatabase();
        properties = dbHandler.getProperties();
        dbHandler.loadDatabase();
        searchHandler = new SearchHandler(properties);
        searchStringsPhrase = new ArrayList<>();
        searchStringsSingleWord = new ArrayList<>();
    }

//    @BeforeClass
//    public static void loadSearchStringsSingleWord() {
//        searchStringsSingleWord.add("singing");
//        searchStringsSingleWord.add("1234");
//        searchStringsSingleWord.add("1234567");
//        searchStringsSingleWord.add("sing");
//        searchStringsSingleWord.add("!@#$%^&*");
//        searchStringsSingleWord.add("w8nt");
//        searchStringsSingleWord.add(" ");
//    }

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

}