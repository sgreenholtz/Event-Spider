package crawler;

import java.util.*;

/**
 * Simple crawler patterned on tutorial from http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/
 * @author Sebastian Greenholtz
 */
public class Crawler {
    private static final int MAX_PAGES_TO_SEARCH = 10;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();

    /**
     * Gets the next URL to visit from pagesToVisit that hasn't already
     * been visited
     * @return next URL to search
     */
    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        } while(this.pagesVisited.contains(nextUrl));

        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }

    /**
     * Searches for a given word on the website at give URL
     * @param url page to search
     * @param searchWord word to search for
     */
    public void search(String url, String searchWord)
    {
        while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
        {
            String currentUrl;
            Leg leg = new Leg();
            if(this.pagesToVisit.isEmpty()) {
                currentUrl = url;
                this.pagesVisited.add(url);
            } else {
                currentUrl = this.nextUrl();
            }

            leg.crawl(currentUrl);

            if(leg.searchForWord(searchWord)) {
                System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                break;
            }
            this.pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
    }
}
