package service;

import java.util.*;
import java.io.*;

/**
 * Super class for parsers into Google Event format
 * @author Sebastian Greenholtz
 */
public abstract class EventJSONParser {

    private String url;

    public EventJSONParser(String url) {
        this.url = url;
    }

    public abstract ArrayList<String> getEventJSONs() throws IOException, TagNotFoundExecption;
}
