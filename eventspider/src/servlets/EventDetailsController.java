package servlets;

import beans.EventBean;
import beans.EventFactory;
import database.EventHandler;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        EventBean eventBean = null;
        if (request.getParameter("returnPage").equals("eventfulSearch")) {
            Map<Integer, EventBean> eventsMap = (HashMap) request.getSession().getAttribute("eventsMap");
            eventBean = eventsMap.get(new Integer(request.getParameter("id")));
        } else {
            properties = (Properties) getServletContext().getAttribute("appProperties");
            eventBean = createEventBean(new Integer(request.getParameter("id")));
        }

        request.setAttribute("event", eventBean);
        request.setAttribute("returnPage", request.getParameter("returnPage"));
        String url = "/event-details";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Takes the event ID and creates a new event bean corresponding to that ID
     * @param eventID Integer ID for the event to create a bean of
     * @return Event Bean of that event
     */
    private EventBean createEventBean(Integer eventID) {
        EventHandler eventHandler = new EventHandler(properties);
        ResultSet results = eventHandler.getEventByID(eventID);
        EventBean eventBean = null;
        try {
            results.last();
            eventBean = EventFactory.createBean(results);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return eventBean;
    }
}