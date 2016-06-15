package servlets;

import dbOperations.AddEvent;

import java.io.*;
import java.lang.reflect.Array;
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

        ArrayList<String> formList = getFormValues(request);

        Properties properties = (Properties) getServletContext().getAttribute("appProperties");
        AddEvent eventAdder = new AddEvent(properties);
        eventAdder.insertEvent(formList);

        String url = "/";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Creates an array list of the form values submitted from the http request
     * @param request HttpServletRequest
     * @return an Array List of the values from the form
     */
    private ArrayList<String> getFormValues(HttpServletRequest request) {
        ArrayList<String> formList = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            for (String value : entry.getValue()) {
                formList.add(value);
            }
        }
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

        return dateTime;
    }
}