package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class does one this: loads a properties file. For use in contexts outside
 * the servlet that loads the properties file.
 * @author Sebastian Greenholtz
 */
public class PropertiesLoader {

    private static Properties properties;

//    /**
//     * Constructor takes a path to the file containing the properties
//     * and loads that file into the Properties instance variable
//     * @param filepath Path in String form to the Properties file
//     */
//    public PropertiesLoader(String filepath) {
//        this.properties = loadProperties(filepath);
//    }

    /**
     * Gets the Properties.
     * @return properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Loads the properties file and returns it as a
     * Properties object
     * @return properties object from the given file
     */
    public static Properties loadProperties(String filepath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filepath));
        } catch(IOException ioe) {
            System.out.println("Can't load the properties file");
            ioe.printStackTrace();
        }
        return properties;
    }

    public Properties loadPropertiesNotStatic(String filepath) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(filepath);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.out.println("Input stream is null");
            }
        } catch(IOException ioe) {
            System.out.println("Can't load the properties file");
            ioe.printStackTrace();
        }
        return properties;
    }
}
