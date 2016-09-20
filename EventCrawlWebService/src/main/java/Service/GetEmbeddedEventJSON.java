package Service;

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
    private boolean fileParse;

    /**
     * Constructor sets the URL of the page to search
     * @param url Page to search for tags
     */
    public GetEmbeddedEventJSON(String url) {
        super(url);
        this.fileParse = false;
        htmlTag = "script[type=\"application/ld+json\"]";
    }

    /**
     * Constructor sets the URL of the page or document to search
     * @param url Page to search for tags
     * @param fileParse True if the content to parse is in a file, false if the
     *                  content is a webpage
     */
    public GetEmbeddedEventJSON(String url, boolean fileParse) {
        super(url);
        this.fileParse = fileParse;
        htmlTag = "script[type=\"application/ld+json\"]";
    }

    /**
     * Gets the elements from the page containing the Event JSON objects
     * and returns an ArrayList of those JSON objects as strings
     * @return ArrayList of String representation of JSON object
     * @throws IOException
     */
    public ArrayList<String> getEventJSONs() throws Exception {
        Document doc = getDocument();
        ArrayList<String> JSONList = new ArrayList<>();
        Elements scriptTags = doc.select(htmlTag);
        for (Element tag : scriptTags) {
            JSONList.add(tag.data());
        }
        if (JSONList.size()==0) {
            throw new TagNotFoundExecption("No JSON event objects exist on this page");
        }
        return JSONList;
    }

    /**
     * Gets the document to parse from web or file
     * @return Document to parse for JSONs
     * @throws IOException
     */
    private Document getDocument() throws IOException {
        Document doc = null;
        if (fileParse) {
            doc = Jsoup.parse(new File(url), "UTF-8");
        } else {
            doc = Jsoup.connect(super.url).get();
        }
        return doc;
    }

}
