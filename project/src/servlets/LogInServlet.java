package servlets;

import dbOperations.UserHandler;

import java.io.*;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Handles the attempted log in from the Log In jsp
 * @author Sebastian Greenholtz
 */

public class LogInServlet extends HttpServlet {

    /**
     * Gets the email and password entered in the log in jsp and checks whether the
     * information represents a registered user. If the information is incorrect, the
     * user is redirected back to the log in page with an error message. If the login
     * is successful, the user ID is place in the Session and the user is redirected to
     * the home page.
     *
     * @param request Http Request
     * @param response Http Response
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Properties properties = (Properties) getServletContext().getAttribute("appProperties");
        UserHandler userHandler = new UserHandler(properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password"));
        Integer userID = userHandler.logIn(email, password);


        String url = "/";
        if (isNotCorrectLogin(userID)) {
            url += "login";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("userID", userID);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Tests whether user ID returned represents an incorrect logIn
     * @param userID Value returned by the UserHandler representing user
     * @return true if the log in information did not bring up a user ID
     */
    private boolean isNotCorrectLogin(Integer userID) {
        if (userID == -1) {
            return true;
        }
        return false;
    }

}