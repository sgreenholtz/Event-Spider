package servlets;

import dbOperations.SearchHandler;

import java.io.*;
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


    }
}