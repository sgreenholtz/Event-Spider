package lucene;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import database.PropertiesLoader;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/**
 * Tests the functioning of the Lucene indexer and searcher. First performs an
 * operation on the Events database, then searches in that result.
 * @author Sebastian Greenholtz
 */
public class LuceneTester {

    private static String indexDir = System.getProperty("java.io.tmpdir") + "indexes";

    public static void main(String[] args) {
        System.out.println(indexDir);
        try {
            LuceneTester tester = new LuceneTester();
            tester.emptyIndex();
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
        TopDocs hits = searcher.search(searchQuery);
        System.out.println(hits.totalHits + " events found.");
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "+ doc.getFields());
        }
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