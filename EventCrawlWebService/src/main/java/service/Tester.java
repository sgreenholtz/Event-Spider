package service;

import java.io.File;
import java.util.*;

/**
 * @author Sebastian Greenholtz
 */
public class Tester {

    public static void main(String[] args) {
        String url = "eventservice.properties";
        PropertiesLoader loader = new PropertiesLoader(url);
        System.out.println(loader.PROPERTIES);
    }
}
