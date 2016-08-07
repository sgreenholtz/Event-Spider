package service;

import java.io.*;
import java.util.Properties;

/**
 * This class does one thing: loads a properties file.
 * @author Sebastian Greenholtz
 */
public class PropertiesLoader {

    public static Properties PROPERTIES;

    /**
     * Constructor takes a path to the file containing the properties
     * and loads that file into the Properties instance variable
     * @param filepath Path in String form to the Properties file
     */
    public PropertiesLoader(String filepath) {
        PROPERTIES = loadProperties(filepath);
    }

    /**
     * Loads the properties file and returns it as a
     * Properties object
     * @return properties object from the given file
     */
    private Properties loadProperties(String filepath) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filepath);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.out.println("Error loading properties file");
            }
        } catch(IOException ioe) {
            System.out.println("Can't load the properties file");
            ioe.printStackTrace();
        }
        return properties;
    }
}
