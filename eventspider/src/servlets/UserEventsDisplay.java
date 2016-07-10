package servlets;

import beans.EventBean;
import beans.EventFactory;
import database.UserHandler;

import java.io.*;
import java.sql.ResultSet;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 * Gets a list of events for a given user and sends them to the
 * "My Events" page
 * @author Sebastian Greenholtz
 */
public class UserEventsDisplay extends HttpServlet {

    private static Properties properties;

    /**
     * Gets all the events associated with given user and sets them in the
     * session to be accessed on the My Events page
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        properties = (Properties) getServletContext().getAttribute("appProperties");
        UserHandler userHandler = new UserHandler(properties);
        ResultSet eventsResults = userHandler.getEventsForUser((Integer) request.getSession().getAttribute("userID"));
        EventFactory eventFactory = new EventFactory(eventsResults);
        Map<Integer, EventBean> eventsMap = eventFactory.getEventMap();

        request.getSession().setAttribute("userEventsMap", eventsMap);

        String url = "/my-events";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}
