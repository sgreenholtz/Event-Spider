package service;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

/**
 * Some websites already have a JSON object for the events on the page. This class
 * grabs those HTML tags and saves the JSON objects
 * @author Sebastian Greenholtz
 */
public class GetEmbeddedEventJSON extends EventJSONParser {

    private String htmlTag;
    private String url;

    /**
     * Constructor sets the URL of the page to search
     * @param url Page to search for tags
     */
    public GetEmbeddedEventJSON(String url) {
        super(url);
        htmlTag = "script[type=\"application/ld+json\"]";
    }

    /**
     * Gets the elements from the page containing the Event JSON objects
     * and returns an ArrayList of those JSON objects as strings
     * @return ArrayList of String representation of JSON object
     * @throws IOException
     */
    public ArrayList<String> getEventJSONs() throws IOException {
        Document doc = Jsoup.connect(url).get();
        ArrayList<String> JSONList = new ArrayList<>();
        Elements scriptTags = doc.select(htmlTag);
        for (Element tag : scriptTags) {
            JSONList.add(tag.data());
        }
        try {
            if (JSONList.size()==0) {
                throw new TagNotFoundExecption("No JSON event objects exist on this page");
            }
        } catch (TagNotFoundExecption tagNotFoundExecption) {
            tagNotFoundExecption.printStackTrace();
        }
        return JSONList;
    }

}
