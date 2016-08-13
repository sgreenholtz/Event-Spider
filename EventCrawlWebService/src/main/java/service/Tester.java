package service;

import java.io.IOException;

/**
 * Tests other classes
 * @author Sebastian Greenholtz
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        GetEmbeddedEventJSON test = new GetEmbeddedEventJSON("EventCrawlWebService/src/test/resources/test.html", true);
        for (String json : test.getEventJSONs()) {
            System.out.println(json);
        }
    }
}
