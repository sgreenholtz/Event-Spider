package testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.EventJSONParser;
import service.RSStoJSON;
import service.UrlNotRssException;

import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * @author Sebastian Greenholtz
 */
public class RSStoJSONTest {

    private ArrayList<String> jsonList;
    private BufferedReader reader;

    @Before
    public void setUp() throws Exception {
        jsonList = new ArrayList<>();
        getResultFileReader();
    }

    @After
    public void tearDown() throws Exception {
        reader.close();
    }

    private void getResultFileReader() {
        try {
            reader = new BufferedReader(
                    new FileReader("src/test/resources/RSSJSONResult"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void getJSONsFromRSSFile() throws Exception {
        EventJSONParser test = new RSStoJSON("https://25livepub.collegenet.com/calendars/PerformanceandSpecialEvents.rss");
        jsonList = test.getEventJSONs();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getEventJSONsTestFailure() throws Exception {
        EventJSONParser test = new RSStoJSON("http://javadox.com/rome/rome/1.0/overview-summary.html");
        exception.expect(UrlNotRssException.class);
        test.getEventJSONs();
    }

    @Test
    public void getEventJSONsTestSuccess() throws Exception {
        getJSONsFromRSSFile();
        int i = 0;
        while (reader.ready()) {
            assertEquals(reader.readLine(), jsonList.get(i).trim());
            i++;
        }
    }

}