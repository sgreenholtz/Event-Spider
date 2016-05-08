package crawler;

/**
 * Tests the crawler application on the command line
 * @author Sebastian Greenholtz
 */
public class CrawlerTest {
    public static void main(String[] args) {
        KeywordCrawler crawler = new KeywordCrawler("event");
        crawler.search("http://isthmus.com", "RSVP");
        for (String link : crawler.getKeywordLinks()) {
            System.out.println(link);
        }
    }
}
