package testing;

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

    @BeforeClass
    public static void setUp() {
        initiateArray();

    }

    private static void initiateArray() {
        eventArray = new ArrayList<>();
    }

//    @Test
//    public static void getEventJSONsSuccessfulTest() {
//        GetEmbeddedEventJSON jsonFetcher = new GetEmbeddedEventJSON("");
//    }

    @Test(expected = TagNotFoundExecption.class)
    public void getEventJSONsFailTest() throws IOException, TagNotFoundExecption {
        GetEmbeddedEventJSON jsonFetcher = new GetEmbeddedEventJSON("http://isthmus.com/news");
        eventArray = jsonFetcher.getEventJSONs();
    }

}