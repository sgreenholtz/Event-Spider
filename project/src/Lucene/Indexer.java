package Lucene;

import java.io.*;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.analysis.en.*;
import org.apache.lucene.document.*;
import org.apache.lucene.store.*;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;


/**
 * Indexes database data, based on Tutorialspoint code but heavily modified.
 * @author Sebastian Greenholtz
 */
public class Indexer {

    private IndexWriter writer;

    /**
     * Constructor creates new Indexer with a simple English Analyzer
     * using the given path as the directory to store indexes
     * @param indexDirectoryPath Path to the directory where indexes should be stored
     * @throws IOException
     */
    public Indexer(String indexDirectoryPath) throws IOException {
        Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
        writer = new IndexWriter(indexDirectory, new IndexWriterConfig(new EnglishAnalyzer()));
    }

    /**
     * Closes the IndexWriter
     * @throws CorruptIndexException
     * @throws IOException
     */
    public void close() throws CorruptIndexException, IOException{
        writer.close();
    }

    /**
     * Creates a document with the ID and Title fields
     * @param id event_id field, Integer
     * @param title title field, String
     * @return Document of fields
     * @throws IOException
     */
    private Document getDocument(Integer id, String title) throws IOException {
        Document document = new Document();
        document.add(new StoredField("event_id", id));
        document.add(new TextField("title", title, Field.Store.YES));
        return document;
    }

    /**
     * Indexes the Result Set from a database query using the Event ID and the
     * Title fields
     * @param results Result Set from the database Select query
     * @throws IOException
     * @throws SQLException
     */
    private void indexResultSet(ResultSet results) throws IOException, SQLException {
        while (results.next()) {
            Document document = getDocument(results.getInt("event_id"), results.getString("title"));
            writer.addDocument(document);
        }
    }

    public int createIndex(String dataDirPath, FileFilter filter)
            throws IOException{
        //get all files in the data directory
        File[] files = new File(dataDirPath).listFiles();

        for (File file : files) {
            if(!file.isDirectory()
                    && !file.isHidden()
                    && file.exists()
                    && file.canRead()
                    && filter.accept(file)
                    ) {
//                indexResultSet();
            }
        }
        return writer.numDocs();
    }
}
