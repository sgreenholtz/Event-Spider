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

    private void getResultFileReader() {
        try {
            reader = new BufferedReader(
                    new FileReader("src/test/resources/RSSJSONResult"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        reader.close();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getEventJSONsTestFailure() throws IOException {
        try {
            EventJSONParser test = new RSStoJSON("http://javadox.com/rome/rome/1.0/overview-summary.html");
            test.getEventJSONs();
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("URL given does not point to an RSS feed"));
        }

    }

}