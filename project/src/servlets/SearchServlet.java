package servlets;

import beans.EventBean;
import beans.EventFactory;
import dbOperations.SearchHandler;

import java.io.*;
import java.sql.ResultSet;
import java.util.Properties;
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

        String location = request.getParameter("location");
        String keyword = request.getParameter("keyword");

        Properties properties = (Properties) getServletContext().getAttribute("appProperties");
        SearchHandler searcher = new SearchHandler(properties);
        ResultSet results = searcher.performTitleSearch(keyword);
        EventFactory eventFactory = new EventFactory(results);
        eventFactory.createBeans();

        request.getServletContext().setAttribute("events", eventFactory.getEventList());
        String url = "/search-result-list";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}