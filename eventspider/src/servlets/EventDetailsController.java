package servlets;

import beans.EventBean;
import beans.EventFactory;
import database.EventHandler;

import java.io.*;
import java.sql.ResultSet;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Controls the event details view and selects the event to view
 * @author Sebastian Greenholtz
 */

public class EventDetailsController extends HttpServlet {

    private static Properties properties;

    /**
     * Gets the value from the search display view to select an event
     * @param request
     * @param response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        properties = (Properties) getServletContext().getAttribute("appProperties");
        EventHandler eventHandler = new EventHandler(properties);
        ResultSet results = eventHandler.getEventByID(new Integer(request.getParameter("eventID")));
        EventFactory eventFactory = new EventFactory(results);
        EventBean eventBean = eventFactory.createBean();
        request.setAttribute("event", eventBean);

        String url = "/event-details";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}