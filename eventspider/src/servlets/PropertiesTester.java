package servlets;

import java.util.*;
import database.PropertiesLoader;

/**
 * @author Sebastian Greenholtz
 */
public class PropertiesTester {
    public static void main(String[] args) {
        Properties properties = PropertiesLoader.loadProperties("/resources/localhost.properties");
        System.out.println(properties.getProperty("hello.world"));
    }
}
