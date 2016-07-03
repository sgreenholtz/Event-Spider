package servlets;

import database.AddEvent;

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

        Map<String, String[]> formMap = dateTimeFormHandler(request);
        ArrayList<String> formList = getFormValues(formMap);

        Properties properties = (Properties) getServletContext().getAttribute("appProperties");
        AddEvent eventAdder = new AddEvent(properties);
        eventAdder.insertEvent(formList);

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
    private Map<String, String[]> dateTimeFormHandler(HttpServletRequest request) {
        Map<String, String[]> formMap = new HashMap(request.getParameterMap());
        String startDateTime = formatDateTime(formMap.get("startDate")[0], formMap.get("startTime")[0]);
        String endDateTime = formatDateTime(formMap.get("endDate")[0], formMap.get("endTime")[0]);
        formMap.remove("startDate");
        formMap.remove("startTime");
        formMap.remove("endDate");
        formMap.remove("endTime");
        formMap.put("startDateTime", new String[]{startDateTime});
        formMap.put("endDateTime", new String[]{endDateTime});
        return formMap;
    }

    /**
     * Creates an array list of the form values submitted from the form
     * using the edited map
     * @param requestMap a mutible map formatted like the parameter map of the HTTP request
     * @return an Array List of the values from the form
     */
    private ArrayList<String> getFormValues(Map<String, String[]> requestMap) {
        ArrayList<String> formList = new ArrayList<>();
        formList.add(requestMap.get("title")[0]);
        formList.add(requestMap.get("url")[0]);
        formList.add(requestMap.get("description")[0]);
        formList.add(requestMap.get("startDateTime")[0]);
        formList.add(requestMap.get("endDateTime")[0]);
        formList.add(requestMap.get("address")[0]);
        formList.add(requestMap.get("city")[0]);
        formList.add(requestMap.get("state")[0]);
        formList.add(requestMap.get("zipcode")[0]);
        return formList;
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