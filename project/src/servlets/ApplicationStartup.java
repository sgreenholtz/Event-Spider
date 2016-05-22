package servlets;

import java.io.*;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

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
        context.setAttribute("appProperties", loadProperties("../event-spider.properties"));
    }

    /**
     * Loads the properties file and returns it as a
     * Properties object
     * @return properties object from the given file
     */
    private Properties loadProperties(String filepath) {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(filepath));
        } catch(IOException ioe) {
            System.out.println("Can't load the properties file");
            ioe.printStackTrace();
        }
        return properties;
    }
}
