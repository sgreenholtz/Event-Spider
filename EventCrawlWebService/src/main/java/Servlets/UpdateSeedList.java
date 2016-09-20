package Servlets;

import Service.PropertiesLoader;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Updates the seed.txt file for Nutch crawling
 * @author Sebastian Greenholtz
 */

public class UpdateSeedList extends HttpServlet {

    private Properties properties;
    private String url;

    /**
     * Update seed list in Nutch dir
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        url = request.getParameter("url");
        properties = getProperties();
        updateSeedList();

    }
    /**
     * Updates the seed.txt file with the new URL to crawl
     */
    private void updateSeedList() {
        File seedList = new File(properties.getProperty("seed.file"));
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(seedList))))
        {
            writer.write(url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets the properties file and creates a Properties object
     * @return Properties object
     */
    private Properties getProperties() {
        PropertiesLoader loader = new PropertiesLoader("eventservice.properties");
        return loader.PROPERTIES;
    }
}