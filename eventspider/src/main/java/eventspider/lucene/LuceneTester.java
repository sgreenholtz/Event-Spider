package eventspider.lucene;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import eventspider.DAL.PropertiesLoader;
import org.apache.commons.io.*;
import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

/**
 * Tests the functioning of the Lucene indexer and searcher. First performs an
 * operation on the Events database, then searches in that result.
 * @author Sebastian Greenholtz
 */
public class LuceneTester {

    private static String indexDir;
    private static final Logger logger = Logger.getLogger(LuceneTester.class);

    public static void main(String[] args) {
        indexDir = FileUtils.getUserDirectory() + "/eventspider/indexes";
        try {
            LuceneTester tester = new LuceneTester();
            tester.emptyIndex();
            tester.index();
            tester.search("fiber");
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        } catch (ParseException e) {
            logger.error(e.getStackTrace());
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
        }
    }

    private void search(String searchQuery) throws IOException, ParseException{
        Searcher searcher = new Searcher(indexDir);
        searcher.printSearch(searchQuery);
    }

    private void index() throws IOException, SQLException {
        PropertiesLoader loader = new PropertiesLoader();
        Properties properties = loader.loadPropertiesNotStatic("main/resources/localhost.properties");
        Indexer indexer = new Indexer(indexDir);
        indexer.createIndex(properties);
        indexer.close();
    }

    private void emptyIndex() throws IOException {
        FileUtils.cleanDirectory(new File(indexDir));

    }
}