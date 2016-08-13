package service;

import java.io.IOException;

/**
 * Tests other classes
 * @author Sebastian Greenholtz
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        EventJSONParser test = new RSStoJSON("https://25livepub.collegenet.com/calendars/PerformanceandSpecialEvents.rss");
        for (String json : test.getEventJSONs()) {
            System.out.println(json);
        }
    }
}
