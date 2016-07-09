package servlets;

import beans.EventBean;
import database.AddEvent;
import database.RecordNotAddedException;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Add the given event to the list for the logged in user
 * @author Sebastian Greenholtz
 */

public class AddEventToUser extends HttpServlet {

    private static EventBean event;
    private static Properties properties;
    private static String addedMessage;
    private static Integer errorCode;
    private static Integer EVENT_NOT_ADDED = 1;
    private static Integer EVENT_EXISTS_IN_DATABASE = 0;

    /**
     * Adds the given event to the user's page, then
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        properties = (Properties) getServletContext().getAttribute("appProperties");
        Map<Integer, EventBean> eventsMap = (HashMap) request.getSession().getAttribute("eventsMap");
        event = eventsMap.get(new Integer(request.getParameter("id")));

        if (!addEventToEventsTable() && errorCode == EVENT_NOT_ADDED) {
            goBackToEventPage(request, response);
        }

        // if successful adding or event already added, add event + user to that table

        goBackToEventPage(request, response);
    }

    /**
     * Adds the event to the event table if the event doesn't already exist
     * @return True if the event was added
     * @return False with appropriate error code if event exists or wasn't added at all
     */
    private boolean addEventToEventsTable() {
        AddEvent addEvent = new AddEvent(properties);
        if (addEvent.eventExistsInDatabase(event.getEventId())) {
            errorCode = EVENT_EXISTS_IN_DATABASE;
            return false;
        }
        try {
            addEvent.addEvent(event);
        } catch (RecordNotAddedException ex) {
            addedMessage = properties.getProperty("fail.message");
            errorCode = EVENT_NOT_ADDED;
            return false;
        }
        return true;
    }

    /**
     * Processes the forward request and takes user back to the Event page
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void goBackToEventPage(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        String url = "/eventDetails?id=" + event.getEventId();
        request.setAttribute("addedMessage", addedMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}