package eventspider.utility;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * This class does one this: loads a properties file. For use in contexts outside
 * the servlet that loads the properties file.
 * @author Sebastian Greenholtz
 */
public class PropertiesLoader {

    private static Properties properties;
    private static final Logger logger = Logger.getLogger(PropertiesLoader.class);

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
            logger.error("Can't load the properties file");
            logger.error(ioe);
        }
        return properties;
    }

    public Properties loadPropertiesNotStatic(String filepath) {
        Properties properties = new Properties();
        try {
            File file = new File(filepath);
            Reader reader = new FileReader(file);
            if (file.exists()) {
                properties.load(reader);
            } else {
                logger.error("Error reading the properties file");
            }
        } catch(IOException ioe) {
            logger.error("Can't load the properties file");
            logger.error(ioe);
        }
        return properties;
    }
}
