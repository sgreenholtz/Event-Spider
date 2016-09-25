package eventspider.servlets;

import eventspider.beans.EventBean;
import eventspider.beans.EventFactory;
import eventspider.beans.LoggedInUser;
import eventspider.database.UserHandler;

import java.awt.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 * Gets a list of events for a given user and sends them to the
 * "My Events" page
 * @author Sebastian Greenholtz
 */
public class UserEventsDisplay extends HttpServlet {

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
        String url = "";
        if (request.getSession().getAttribute("userID") == null) {
            url = "/login";
        } else {
            UserHandler userHandler = new UserHandler();
            List<EventBean> list = userHandler.getEventsForUser((LoggedInUser) request.getSession().getAttribute("user"));
            EventFactory eventFactory = new EventFactory();
            eventFactory.createBeansMap(list);
            Map<Integer, EventBean> eventsMap = eventFactory.getEventMap();

            request.getSession().setAttribute("userEventsMap", eventsMap);
            request.setAttribute("returnPage", "/myEventsController");
            url = "/my-events";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}
