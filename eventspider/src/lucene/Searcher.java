package lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

/**
 * Performs a search within the index given. Modified from Tutorialspoint code.
 * @author Sebastian Greenholtz
 */
public class Searcher {

    private IndexSearcher indexSearcher;
    private QueryParser queryParser;
    private Query query;

    /**
     * Constructor takes a String to the path of the index directory and
     * instantiates the IndexSearcher and QueryParser
     * @param indexDirectoryPath Path to directory containing indexes
     * @throws IOException
     */
    public Searcher(String indexDirectoryPath) throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDirectoryPath)));
        Analyzer analyzer = new StandardAnalyzer();
        indexSearcher = new IndexSearcher(reader);
        queryParser = new QueryParser(LuceneConstants.CONTENTS, analyzer);
    }

    /**
     * Performs the search on the given search term and returns documents that
     * match the query
     * @param searchQuery Term or terms to search on
     * @return Documents that match the search query
     * @throws IOException
     * @throws ParseException
     */
    public TopDocs search(String searchQuery) throws IOException, ParseException {
        query = queryParser.parse(searchQuery);
        return indexSearcher.search(query, LuceneConstants.MAX_SEARCH);
    }

    /**
     * Gets a single document from the results of the search
     * @param scoreDoc A single result from the TopDocs result
     * @return A document from that results
     * @throws CorruptIndexException
     * @throws IOException
     */
    public Document getDocument(ScoreDoc scoreDoc) throws CorruptIndexException, IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }
}
