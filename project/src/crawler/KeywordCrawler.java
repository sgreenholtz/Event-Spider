package crawler;

import java.util.*;

/**
 * Crawler that searches for a search term in links that contain a specified keyword
 * @author Sebastian Greenholtz
 */
public class KeywordCrawler extends Crawler {
    private String keyword;
    private List<String> keywordLinks;

    /**
     * Constructor with the keyword
     * @param keyword word to search for in links
     */
    public KeywordCrawler(String keyword) {
        super();
        this.keyword = keyword;
        this.keywordLinks = new ArrayList<String>();
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

            leg.searchCrawl(currentUrl, keyword);

            if(leg.searchForWord(searchWord)) {
//                System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                keywordLinks.add(currentUrl);
            }
            this.pagesToVisit.addAll(leg.getLinks());
        }
    }

    /**
     * Gets all the links in keywordLinks
     * @return keywordLinks
     */
    public List<String> getKeywordLinks() {
        return keywordLinks;
    }
}
