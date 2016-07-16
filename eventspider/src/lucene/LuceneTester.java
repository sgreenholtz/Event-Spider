package lucene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import database.PropertiesLoader;
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
//        System.out.println(System.getProperty("java.io.tmpdir")+"indexes");
        try {
            LuceneTester tester = new LuceneTester();
            tester.index();
            tester.readIndex();
//            tester.search("COOP");
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readIndex() throws IOException {
        Searcher searcher = new Searcher(System.getProperty("java.io.tmpdir")+"indexes");
        searcher.getDocsInIndex();
    }

    private void search(String searchQuery) throws IOException, ParseException{
        Searcher searcher = new Searcher(System.getProperty("java.io.tmpdir")+"indexes");
//        System.out.println(searcher.getDocumentCount());
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
}