package servlets;

import eventsbatching.EventfulParser;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Performs a search on Eventful based on given location and returns the results as a
 * JSON object
 * @author Sebastian Greenholtz
 */

public class EventfulSearch extends HttpServlet {

    private static Properties properties;
    private static String jsonURL;

    /**
     * Get the location result from the JSP and send AUTH request to Eventful
     * @param request
     * @param response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        if (request.getSession().getAttribute("returnPage") == null) {
            properties = (Properties) getServletContext().getAttribute("appProperties");
            String searchUrl = constructURL(request.getParameter("location"));
            String sessionID = UUID.randomUUID().toString();
            request.getSession().setAttribute("sessionID", sessionID);
            jsonURL = getJsonUrl(sessionID);

        /* Uncomment this for benchmark testing */
//        double start = System.nanoTime();
//        getJSON(searchUrl);
//        double elapsedTime = (System.nanoTime() - start) / 1000000000.00;
//        System.out.println("Elapsed time: " + elapsedTime);

            getJSON(searchUrl);
            EventfulParser eventfulParser = new EventfulParser(jsonURL);
            request.getSession().setAttribute("eventsMap", eventfulParser.getEventMap());
        }

        request.setAttribute("searchTerm", request.getParameter("location"));
        request.setAttribute("returnPage", "eventfulSearch");

        String url = "/search-result-list";
        if (request.getSession().getAttribute("userID") == null) {
            url = "/errorTestingServlet";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Calls to Eventful based on the URL formed in the Servlet and gets the
     * JSON results returned, then writes them to a temporary file
     * @param url API URL generated based on search term
     * @throws IOException
     */
    private void getJSON(String url) throws IOException {
        URL eventfulURL = new URL(url);
        URLConnection connection = eventfulURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(jsonURL)));
        String inputLine = "";
        while ((inputLine = in.readLine()) != null) {
            write.println(inputLine);
        }
        in.close();
        write.close();
    }

    /**
     * Creates a String representing the URL to send to Eventful to perform search
     * @param location Location user searched
     * @return URL in String form for Eventful search
     */
    private String constructURL(String location) {
        String url = "http://api.eventful.com/json/events/search?";
        url += "app_key=" + properties.getProperty("eventful.key");
        url += "&location=" + constructSearchString(location);
        url += "&page_size=" + 30;
        return url;
    }

    /**
     * Constructs a string that is safe for passing through a URL by taking out non word
     * characters and replacing them with a + sign
     * @param field Field to convert into a URL safe form
     * @return URL safe String of field
     */
    private String constructSearchString(String field) {
        String urlSafeString = "";
        for (String token : field.split("\\W")) {
            urlSafeString += token + "+";
        }
        return urlSafeString.substring(0, urlSafeString.length()-1);
    }

    /**
     * Constructs the URL for the JSON file based on the user ID
     * @param userID ID of the logged in user, from the session
     * @return URL for the JSON file
     */
    private String getJsonUrl(String userID) {
        return System.getProperty("java.io.tmpdir") + "/" + userID + "_eventful.json";
    }

}