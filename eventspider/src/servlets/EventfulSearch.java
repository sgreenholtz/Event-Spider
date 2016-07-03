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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        properties = (Properties) getServletContext().getAttribute("appProperties");
        String searchUrl = constructURL(request.getParameter("location"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>" + searchUrl + "</p>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Creates a String representing the URL to send to Evnetful to perform search
     * @param location Location user searched
     * @return URL in String form for Eventful search
     */
    private String constructURL(String location) {
        String url = "http://api.eventful.com/json/events/search?"; //kvxN9gH3zxsQhvCF&location=Madison";
        url += "app_key=" + properties.getProperty("eventful.api");
        url += "&location=" + constructSearchString(location);
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

}