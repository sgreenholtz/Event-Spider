package service;

import java.io.IOException;

/**
 * Tests other classes
 * @author Sebastian Greenholtz
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        RSStoJSON test = new RSStoJSON("https://25livepub.collegenet.com/calendars/CalendarAcademic.rss");
        test.getEventJSONs();
    }
}
