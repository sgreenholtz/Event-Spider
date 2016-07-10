package servlets;

import java.io.*;
import java.util.*;
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

    }
}
