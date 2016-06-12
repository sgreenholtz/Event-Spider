package testing;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Loads up the database for use in testing
 * @author Sebastian Greenholtz
 */
public class LoadDatabase {

    private Properties properties;
    private Connection conn;

    /**
     * Constructor loads up the properties file for testing
     */
    public LoadDatabase() {
        properties = new Properties();
        try {
            InputStream input = new FileInputStream("project/src/test.properties");
            properties.load(input);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Connection loadConnection() {
        Connection conn = null;

        return conn;
    }


    public static void main(String[] args) {
        LoadDatabase loader = new LoadDatabase();

    }


}
