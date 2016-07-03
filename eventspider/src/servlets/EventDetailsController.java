package servlets;

import beans.EventBean;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Controls the event details view and selects the event to view
 * @author Sebastian Greenholtz
 */

public class EventDetailsController extends HttpServlet {

    /**
     * Gets the value from the search display view to select an event
     * @param request
     * @param response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        Map<String, EventBean> eventsMap = (HashMap) request.getAttribute("eventsMap");
        EventBean eventBean = eventsMap.get(request.getParameter("id"));
        request.setAttribute("event", eventBean);

        String url = "/event-details";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}