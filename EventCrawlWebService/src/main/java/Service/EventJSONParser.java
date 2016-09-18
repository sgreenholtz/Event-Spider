package Service;

import java.util.*;
import java.io.*;

/**
 * Super class for parsers into Google Event format
 * @author Sebastian Greenholtz
 */
public abstract class EventJSONParser {

    protected String url;

    public EventJSONParser(String url) {
        this.url = url;
    }

    public abstract ArrayList<String> getEventJSONs() throws Exception;
}
