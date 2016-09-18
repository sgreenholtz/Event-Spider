package eventspider.lucene;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

/**
 * Performs a search within the index given. Modified from Tutorialspoint code.
 * @author Sebastian Greenholtz
 */
public class Searcher {

    private IndexSearcher indexSearcher;
    private QueryParser queryParser;
    private Query query;
    private IndexReader reader;

    /**
     * Constructor takes a String to the path of the index directory and
     * instantiates the IndexSearcher and QueryParser
     * @param indexDirectoryPath Path to directory containing indexes
     * @throws IOException
     */
    public Searcher(String indexDirectoryPath) throws IOException {
        reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDirectoryPath)));
        Analyzer analyzer = new StandardAnalyzer();
        indexSearcher = new IndexSearcher(reader);
        queryParser = new QueryParser(LuceneConstants.SEARCH_FIELD, analyzer);
    }

    /**
     * Simple method to read through the index, print the title of
     * each document, and return all the documents as an array list
     * @return Array list of all documents in the index
     * @throws IOException
     */
    public ArrayList<Document> getDocsInIndex() throws IOException {
        ArrayList<Document> docs = new ArrayList<>();
        for (int i=0; i<reader.numDocs(); i++) {
            Document doc = reader.document(i);
            System.out.println(doc.get("title"));
            docs.add(doc);
        }
        return docs;
    }

    /**
     * Performs the search on the given search term and returns documents that
     * match the query
     * @param searchQuery Term or terms to search on
     * @return Documents that match the search query
     * @throws IOException
     * @throws ParseException
     */
    private TopDocs doSearch(String searchQuery) throws IOException {
        TopDocs topDocs = null;
        try {
            query = queryParser.parse(searchQuery);
            topDocs = indexSearcher.search(query, LuceneConstants.MAX_SEARCH);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return topDocs;
    }

    /**
     * Performs the search on the given search terms and prints out the results
     * @param searchQuery String to search
     * @throws IOException
     * @throws ParseException
     */
    public void printSearch(String searchQuery) throws IOException {
        TopDocs hits = doSearch(searchQuery);
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = getDocument(scoreDoc);
            System.out.println("ID: "+ doc.get("event_id"));
            System.out.println("Title: "+ doc.get("title"));
        }
    }

    /**
     * Creates a map of event ID -> title from search
     * @param searchQuery String to search for
     * @return Map of event ID (integer) -> title (String) of events found
     */
    public Map<Integer, String> searchMap(String searchQuery) {
        Map<Integer, String> searchMap = new HashMap<>();
        try {
            TopDocs hits = doSearch(searchQuery);
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = getDocument(scoreDoc);
                searchMap.put(new Integer(doc.get("event_id")), doc.get("title"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return searchMap;
    }

    /**
     * Returns an ArrayList of only the IDs of the search results
     * @param searchQuery String query to search on
     * @return Array List of the IDs of the events found in the search
     */
    public ArrayList<Integer> searchList(String searchQuery) {
        ArrayList<Integer> searchIDList = new ArrayList<>();
        try {
            TopDocs hits = doSearch(searchQuery);
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = getDocument(scoreDoc);
                searchIDList.add(new Integer(doc.get("event_id")));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return searchIDList;
    }

    /**
     * Gets a single document from the results of the search
     * @param scoreDoc A single result from the TopDocs result
     * @return A document from that results
     * @throws CorruptIndexException
     * @throws IOException
     */
    public Document getDocument(ScoreDoc scoreDoc) throws IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }
}
