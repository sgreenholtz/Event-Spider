package service;

import java.io.IOException;

/**
 * Tests other classes
 * @author Sebastian Greenholtz
 */
public class Tester {

    public static void main(String[] args) throws IOException, TagNotFoundExecption {
        GetEmbeddedEventJSON tester = new GetEmbeddedEventJSON("http://isthmus.com/news");
        System.out.println(tester.getEventJSONs());
    }
}
