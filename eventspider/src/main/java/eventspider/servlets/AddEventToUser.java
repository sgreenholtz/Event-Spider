package eventspider.servlets;

import eventspider.beans.EventBean;
import eventspider.database.EventHandler;

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
    private static Integer EVENT_NOT_SAVED_TO_USER = 2;

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
            log("Add event to user error: " + errorCode.toString());
            goBackToEventPage(request, response);
        } else {
            log("Add event to user error: " + errorCode.toString());
            saveEventToUser((Integer) request.getSession().getAttribute("userID"), event.getEventId());
            goBackToEventPage(request, response);
        }
    }

    /**
     * Adds the event to the event table if the event doesn't already exist
     * @return True if the event was added
     * @return False with appropriate error code if event exists or wasn't added at all
     */
    private boolean addEventToEventsTable() {
        EventHandler eventHandler = new EventHandler();
        if (eventHandler.eventExistsInDatabase(event.getEventId())) {
            errorCode = EVENT_EXISTS_IN_DATABASE;
            return false;
        }
//        try {
            eventHandler.addEvent(event);
//        } catch (RecordNotAddedException ex) {
//            addedMessage = properties.getProperty("fail.message");
//            errorCode = EVENT_NOT_ADDED;
//            return false;
//        }
        return true;
    }

    /**
     * Attempts to add the event to the user in the database and updates
     * the event add message. If the add fails, the appropriate error
     * code and fail message is added.
     * @param userID
     * @param eventID
     */
    private void saveEventToUser(Integer userID, Integer eventID) {
        EventHandler eventHandler = new EventHandler();
        if (eventHandler.saveEventToUser(userID, eventID)) {
            addedMessage = properties.getProperty("success.message");
        } else {
            errorCode = EVENT_NOT_SAVED_TO_USER;
            addedMessage = properties.getProperty("fail.message");
        }
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