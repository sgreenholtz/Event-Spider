package service;

import java.util.*;
import java.io.*;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.json.*;

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

    public ArrayList<String> getEventJSONs() throws IOException {
        ArrayList<String> eventJSONs = new ArrayList<>();
        try {
            Document xml = loadTestDocument(url);
            System.out.println(XML.toJSONObject(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventJSONs;
    }

    private static Document loadTestDocument(String url) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new URL(url).openStream());
    }

}
