package lucene;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import database.PropertiesLoader;

/**
 * @author Sebastian Greenholtz
 */
public class LuceneTester {

    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = PropertiesLoader.loadProperties("../localhost.properties");
        Indexer indexer = new Indexer("../../indexes");
        Integer indexCount = indexer.createIndex(properties);
        System.out.println("Created " + indexCount + " indexes");
    }
}
