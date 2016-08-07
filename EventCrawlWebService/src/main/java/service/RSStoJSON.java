package service;

import java.util.*;
import java.io.*;

/**
 * Takes a URL of an RSS feed of events and conversts them into a Google Events Markup
 * JSON
 * @author Sebastian Greenholtz
 */
public class RSStoJSON extends EventJSONParser {

    private String url;

    public RSStoJSON(String url) {
        super(url);
    }

    public ArrayList<String> getEventJSONs() throws IOException, TagNotFoundExecption {
        ArrayList<String> eventJSONs = new ArrayList<>();

        return eventJSONs;
    }

}
