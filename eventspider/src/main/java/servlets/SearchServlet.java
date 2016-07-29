package servlets;

import beans.EventFactory;
import lucene.Searcher;
import database.EventHandler;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This is the servlet for performing a search. Takes in the search data and sends to a view
 * of search results.
 *
 * @author Sebastian Greenholtz
 */

public class SearchServlet extends HttpServlet {

    /**
     * Gets the search terms and performs a search in the database
     *
     * @param request Http Request
     * @param response Http Response
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String searchTerm = request.getParameter("keyword");

        Properties properties = (Properties) getServletContext().getAttribute("appProperties");
        Searcher searcher = new Searcher(properties.getProperty("index.dir"));
        EventHandler eventHandler = new EventHandler(properties);
        Map<Integer, String> eventsMap = searcher.searchMap(searchTerm);
        ArrayList<Integer> eventIDsList = searcher.searchList(searchTerm);
        ResultSet events = eventHandler.getEventByID(eventIDsList);
        EventFactory eventFactory = new EventFactory(events);
        eventFactory.createBeansMap();

        request.getSession().setAttribute("eventsMap", eventFactory.getEventMap());
        request.getSession().setAttribute("searchTerm", searchTerm);
        String url = "/search-result-list";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}