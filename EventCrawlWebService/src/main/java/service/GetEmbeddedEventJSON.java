package service;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * Some websites already have a JSON object for the events on the page. This class
 * grabs those HTML tags and saves the JSON objects
 * @author Sebastian Greenholtz
 */
public class GetEmbeddedEventJSON {

    private String htmlTag;
    private String url;

    /**
     * Empty constructor
     */
    public GetEmbeddedEventJSON() {
        htmlTag = "<script type=\"application/ld+json\">";
    }

    /**
     * Constructor sets the URL of the page to search
     * @param url Page to search for tags
     */
    public GetEmbeddedEventJSON(String url) {
        this();
        this.url = url;
    }

    /**
     * Gets the value of url.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    public static void main(String[] args) throws IOException {
        GetEmbeddedEventJSON test = new GetEmbeddedEventJSON("http://isthmus.com/search/event/calendar-of-events/");
        Document doc = Jsoup.connect(test.getUrl()).get();
        Elements scriptTags = doc.select("script[type=\"application/ld+json\"]");
        for (Element tag : scriptTags) {
            System.out.println(tag.data());
        }
    }

}
