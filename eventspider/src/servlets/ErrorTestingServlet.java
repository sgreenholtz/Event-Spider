package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author Sebastian Greenholtz
 */

public class ErrorTestingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String url = "/search-result-list";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }


}