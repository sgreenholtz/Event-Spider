package lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

import database.PropertiesLoader;
import org.apache.commons.io.*;
import org.apache.lucene.queryparser.classic.ParseException;

/**
 * Tests the functioning of the Lucene indexer and searcher. First performs an
 * operation on the Events database, then searches in that result.
 * @author Sebastian Greenholtz
 */
public class LuceneTester {

    private static String indexDir;

    public static void main(String[] args) {
        indexDir = FileUtils.getUserDirectory() + "/eventspider/diy/tomcat/indexes";
        try {
            LuceneTester tester = new LuceneTester();
//            tester.emptyIndex();
            tester.index();
            tester.search("fiber");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void search(String searchQuery) throws IOException, ParseException{
        Searcher searcher = new Searcher(indexDir);
        searcher.printSearch(searchQuery);
    }

    private void index() throws IOException, SQLException {
        PropertiesLoader loader = new PropertiesLoader();
        Properties properties = loader.loadPropertiesNotStatic("/localhost.properties");
        Indexer indexer = new Indexer(indexDir);
        indexer.createIndex(properties);
        indexer.close();
    }

    private void emptyIndex() throws IOException {
        FileUtils.cleanDirectory(new File(indexDir));

    }
}