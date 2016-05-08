package crawler;

import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.Elements;

import java.util.*;
import java.io.*;

/**
 * One leg of the crawling journey. Scapes all the data from a webpage and looks for the
 * given word, plus links to visit next
 * @author Sebastian Greenholtz
 */
public class Leg {
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links;
    private Document htmlDocument;

    /**
     * Empty constructor
     */
    public Leg() {
       links = new LinkedList<String>();
    }

    /**
     * Brings up a given webpage and searches for links on the page
     * @param url page to search
     */
    public void crawl(String url) {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;

            System.out.println("Received web page at " + url);

            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            for(Element link : linksOnPage) {
                this.links.add(link.absUrl("href"));
            }
        } catch(IOException ioe) {
            System.out.println("Error in out HTTP request " + ioe);
            ioe.printStackTrace();
        }
    }

    /**
     * Searches for a given word on the webpage
     * @param searchWord word to search for
     * @return true if the page contains the word
     */
    public boolean searchForWord(String searchWord)
    {
        if(this.htmlDocument == null) {
            System.out.println("ERROR! Call crawl() before performing analysis on the document");
            return false;
        }
        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }

    /**
     * Gets the links found on the page
     * @return links List of links found on the page searched
     */
    public List<String> getLinks() {
        return links;
    }
}
