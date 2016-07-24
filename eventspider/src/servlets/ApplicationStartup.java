package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import database.PropertiesLoader;

/**
 * This servlet runs on startup. It loads up the properties file into the Servlet context
 * @author Sebastian Greenholtz
 */
@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/startup" },
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet {
    /**
    * Performs initializing task: loading properties file
    */
    public void init() {
        ServletContext context = this.getServletContext();
//        context.setAttribute("appProperties", PropertiesLoader.loadProperties("../openshift.properties"));
        context.setAttribute("appProperties", PropertiesLoader.loadProperties("eventspider/resources/localhost.properties"));
    }

}
