package testing;

import Service.GetEmbeddedEventJSON;
import Service.TagNotFoundExecption;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;
import java.io.*;

/**
 * Tests the GetEmbeddedEventJSON class
 * @author Sebastian Greenholtz
 */
public class GetEmbeddedEventJSONTest {

    private static ArrayList<String> eventArray;
    private static BufferedReader readFile;

    @BeforeClass
    public static void setUp() throws Exception {
        initiateArray();
        getResultFile();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        readFile.close();
    }

    private static void initiateArray() {
        eventArray = new ArrayList<>();
    }

    private static void getResultFile() throws Exception {
        readFile = new BufferedReader(
                new FileReader("src/test/resources/resultJSONsGoogleEventMarkup"));
    }

    private void getEventArray() throws Exception {
        GetEmbeddedEventJSON jsonFetcher =
                new GetEmbeddedEventJSON("src/test/resources/googleEventMarkupEmbedded.html",
                true);
        eventArray = jsonFetcher.getEventJSONs();
    }

    @Test(expected = TagNotFoundExecption.class)
    public void getEventJSONsFailTest() throws Exception {
        GetEmbeddedEventJSON jsonFetcher = new GetEmbeddedEventJSON("http://isthmus.com/news");
        eventArray = jsonFetcher.getEventJSONs();
    }

    @Test
    public void getEventJSONsSuccessfulTest() throws Exception {
        getEventArray();
        int i = 0;
        while (readFile.ready()) {
            assertEquals(readFile.readLine(), eventArray.get(i).trim());
            i++;
        }
    }



}