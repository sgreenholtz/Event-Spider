package Service;

import java.net.URL;
import java.io.*;
import java.util.*;

import com.rometools.rome.feed.module.DCModuleImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

/**
 * Takes a URL of an RSS feed of events and converts them into a Google Events Markup
 * JSON
 * @author Sebastian Greenholtz
 */
public class RSStoJSON extends EventJSONParser {

    /**
     * Constructor takes a URL to an RSS feed
     * @param url URL to an RSS feed
     */
    public RSStoJSON(String url) {
        super(url);
    }

    public ArrayList<String> getEventJSONs() throws IOException, UrlNotRssException {
        if(urlIsRSS()) {
            return syndEntryToJSON();
        } else {
            throw new UrlNotRssException("URL given does not point to an RSS feed");
        }
    }

    /**
     * Checks whether the URL given represents an RSS feed
     * @return True if the URL is an RSS feed
     */
    private boolean urlIsRSS() {
        if (url.endsWith(".rss")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reads an RSS feed from the URL
     * @return a list of SyndEntry objects representing the items
     * in the feed
     */
    private List<SyndEntry> readFeed() {
        List<SyndEntry> feedList = null;
        try {
            URL feedUrl = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            feedList = feed.getEntries();
        } catch (Exception ex){
            System.out.println();
            System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
            System.out.println("The first parameter must be the URL of the feed to read.");
            System.out.println();
            ex.printStackTrace();
        }
        return feedList;
    }

    /**
     * Creates an array list of JSONs from the entries of the feed
     * @return ArrayList of JSONs in String form
     */
    private ArrayList<String> syndEntryToJSON() {
        List<SyndEntry> feedList = readFeed();
        ArrayList<String> jsons = new ArrayList<>();
        for (SyndEntry entry : feedList) {
            jsons.add(formatJSONFromSynd(entry));
        }
        return jsons;
    }

    /**
     * Formats JSON string of Google Event Markup based on entry information
     * @param entry RSS Feed entry
     * @return String of JSON with entry information
     */
    private String formatJSONFromSynd(SyndEntry entry) {
        String json = "{";
        json += "\"@context\": \"http://schema.org\",";
        json += "\"@type\": \"Event\",";
        json += "\"name\": \"" + entry.getTitle() + "\",";
        json += "\"startDate\": \"" + getEntryDate(entry) + "\",";
        json += "\"description\": \"" + entry.getDescription().getValue() + "\",";
        json += "\"url\": \"" + entry.getLink() + "\",";
        json += "\"location\": {";
        json += "\"@type\": \"EventVenue\"";
        json += "}";
        json += "}";
        return json;
    }

    /**
     * Gets the Date associated with the entry
     * @param entry Event entry
     * @return Date object representing the date and time of the event
     */
    private Date getEntryDate(SyndEntry entry) {
        DCModuleImpl mod = (DCModuleImpl) entry.getModule("http://purl.org/dc/elements/1.1/");
        return mod.getDate();
    }
}
