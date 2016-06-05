package servlets;

import dbOperations.AddEvent;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet to add a manually entered event into the database.
 * @author Sebastian Greenholtz
 */

public class AddEventManual extends HttpServlet {

    /**
     * Adds a manually entered event into the database
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        Map<String, String> formMap = getFormMap(request);

        Properties properties = (Properties) getServletContext().getAttribute("appProperties");
        AddEvent eventAdder = new AddEvent(properties);
        eventAdder.insertEvent(formMap);

    }

    /**
     * Gets the form values from the HttpServletRequest variable and creates a map
     * @param request HttpServletRequest
     * @return Map of key=database field value=form value
     */
    private Map<String, String> getFormMap(HttpServletRequest request) {
        Map<String, String> formMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String key = entry.getKey();
            String value = "";
            for (String arrayValue : entry.getValue()) {
                value = arrayValue;
            }
            formMap.put(key, value);
        }
        return formMap;
    }
}