package eventspider.DAL;

import eventspider.beans.*;
import eventspider.database.EventHandler;
import eventspider.lucene.Searcher;

import java.util.*;

/**
 * Searches the database for events that match a given search
 * @author Sebastian Greenholtz
 */
public class DatabaseSearch {

    private SearchBean search;
    private Properties properties;

    /**
     * Assigns search bean as instance variable and properties
     * object to the properties instance
     * @param search Search bean
     * @param properties Properties object
     */
    public DatabaseSearch(SearchBean search, Properties properties) {
        this.search = search;
        this.properties = properties;
    }

    /**
     * Performs a search in the database using the EventHandler DAO object
     * and returns a list of event beans
     * @return List of event beans with the given keyword in the title or description
     * @throws Exception
     */
    public List<EventBean> searchByKeywordOnly() throws Exception {
        EventHandler eventHandler = new EventHandler();
        Searcher searcher = new Searcher(properties.getProperty("index.dir"));
        ArrayList<Integer> eventIDsList = searcher.searchList(search.getKeyword());
        return eventHandler.getEventByID(eventIDsList);
    }

}
