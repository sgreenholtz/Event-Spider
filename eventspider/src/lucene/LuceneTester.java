package lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

    public static void main(String[] args) {
        try {
            LuceneTester tester = new LuceneTester();
            tester.emptyIndex();
            tester.index();
            tester.search("COOP");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void search(String searchQuery) throws IOException, ParseException{
        Searcher searcher = new Searcher(System.getProperty("java.io.tmpdir")+"indexes");
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();

        System.out.println(hits.totalHits +
                " events found. Time :" + (endTime - startTime) +" ms");
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "+ doc.getFields());
        }
    }

    private void index() throws IOException, SQLException {
        PropertiesLoader loader = new PropertiesLoader();
        Properties properties = loader.loadPropertiesNotStatic("/localhost.properties");
        Indexer indexer = new Indexer(System.getProperty("java.io.tmpdir")+"indexes");
        indexer.createIndex(properties);
        indexer.close();
    }

    private void emptyIndex() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("java.io.tmpdir")+"indexes"));

    }
}