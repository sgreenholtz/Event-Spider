package servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Performs a search on Eventful based on given location and returns the results as a
 * JSON object
 * @author Sebastian Greenholtz
 */

public class EventfulSearch extends HttpServlet {

    private static Properties properties;

    /**
     * Get the location result from the JSP and send AUTH request to Eventful
     * @param request
     * @param response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        properties = (Properties) getServletContext().getAttribute("appProperties");

    }

    /**
     * Creates a String representing the URL to send to Evnetful to perform search
     * @param location Location user searched
     * @return URL in String form for Eventful search
     */
    private String constructURL(String location) {
        String url = "http://api.eventful.com/json/events/search?"; //kvxN9gH3zxsQhvCF&location=Madison";
        url += "app_key=" + properties.getProperty("eventful.api");
        url += "&location=" + location;
        return url;
    }

}