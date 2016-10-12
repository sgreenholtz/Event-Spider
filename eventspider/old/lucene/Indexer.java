package eventspider.lucene;

import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
        writer = new IndexWriter(indexDirectory, new IndexWriterConfig(new StandardAnalyzer()));
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
    private Document getDocument(String id, String title) throws IOException {
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
            indexFields(results.getString("event_id"), results.getString("title"));
        }
    }

    /**
     * Adds a document to the index based on the fields themselves
     * @param eventID Integer ID for the event
     * @param title String title for the event
     * @throws IOException
     */
    public void indexFields(String eventID, String title) throws IOException {
        Document document = getDocument(eventID, title);
        writer.addDocument(document);
    }

    /**
     * Gets the ResultSet to index and indexes it, then gets the number of items in the index
     * @param properties Application properties, for IndexingDatabaseHandler
     * @return Number of items in the index
     * @throws IOException
     * @throws SQLException
     */
    public int createIndex(Properties properties) throws IOException, SQLException {
        IndexingDatabaseHandler databaseHandler = new IndexingDatabaseHandler(properties);
        indexResultSet(databaseHandler.getResults());
        return writer.numDocs();
    }
}
