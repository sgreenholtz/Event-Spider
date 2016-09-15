package servlets;

import database.EventHandler;

import java.io.*;
import java.util.ArrayList;
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

        Map<String, String> formMap = dateTimeFormHandler(request);
        EventHandler eventAdder = new EventHandler();
        eventAdder.insertEvent(formMap);

        String url = "/";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Uses the date and time fields from the request to call the datetime formatter,
     * then removes the date and time objects from the parameter map, adds the new
     * values, and places the map as an attribute in the request
     * @param request HttpServletRequest
     * @return a Map mirroring the parameter map in variables with the edited fields
     */
    private Map<String, String> dateTimeFormHandler(HttpServletRequest request) {
        Map<String, String> formMap = new HashMap<String, String>();
        formMap.put("title", request.getParameter("title"));
        formMap.put("url", request.getParameter("url"));
        formMap.put("description", request.getParameter("description"));
        formMap.put("address", request.getParameter("address"));
        formMap.put("city", request.getParameter("city"));
        formMap.put("state", request.getParameter("state"));
        formMap.put("zipcode", request.getParameter("zipcode"));
        String startDateTime = formatDateTime(request.getParameter("startDate"), request.getParameter("startTime"));
        String endDateTime = formatDateTime(request.getParameter("endDate"), request.getParameter("endTime"));
        formMap.put("startDateTime", startDateTime);
        formMap.put("endDateTime", endDateTime);
        return formMap;
    }

    /**
     * Creates a single string representing the datetime value in format for MySql:
     * YYYY-MM-DD HH:MM:SS
     * @param date String of the date from form
     * @param time String of the time from form
     * @return String of datetime
     */
    private String formatDateTime(String date, String time) {
        String dateTime = "";
        dateTime += date + " " + time + ":00";
        return dateTime;
    }
}