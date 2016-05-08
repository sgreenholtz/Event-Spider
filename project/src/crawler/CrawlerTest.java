package crawler;

/**
 * Tests the crawler application on the command line
 * @author Sebastian Greenholtz
 */
public class CrawlerTest {
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.search("http://madisoncollege.edu", "Event");
    }
}
