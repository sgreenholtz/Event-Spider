package testing;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.*;

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
    public static void setUp() {
        initiateArray();
        getResultFile();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        readFile.close();
    }

    private static void initiateArray() {
        eventArray = new ArrayList<>();
    }

    private static void getResultFile() {
        try {
            readFile = new BufferedReader(
                    new FileReader("src/test/resources/resultJSONsGoogleEventMarkup"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void getEventArray() throws IOException {
        GetEmbeddedEventJSON jsonFetcher =
                new GetEmbeddedEventJSON("src/test/resources/googleEventMarkupEmbedded.html",
                true);
        eventArray = jsonFetcher.getEventJSONs();
    }

    @Test(expected = TagNotFoundExecption.class)
    public void getEventJSONsFailTest() throws IOException, TagNotFoundExecption {
        GetEmbeddedEventJSON jsonFetcher = new GetEmbeddedEventJSON("http://isthmus.com/news");
        eventArray = jsonFetcher.getEventJSONs();
    }

    @Test
    public void getEventJSONsSuccessfulTest() throws IOException {
        getEventArray();
        int i = 0;
        while (readFile.ready()) {
            assertEquals(readFile.readLine(), eventArray.get(i).trim());
            i++;
        }
    }



}