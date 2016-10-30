package eventspider.DAL;

import com.fasterxml.jackson.databind.ObjectMapper;
import eventspider.Eventful.EventItem;
import eventspider.Eventful.EventfulResponse;
import eventspider.Eventful.EventsList;
import eventspider.beans.EventBean;
import eventspider.beans.EventFactory;
import eventspider.beans.SearchBean;
import eventspider.utility.WebServiceUtility;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Methods to facilitate the calls to Eventful API
 * @author Sebastian Greenholtz
 */
public class EventfulSearch {

    private static final String eventfulKey = "sBbJgh96n2W9JV4m";
    private static final Logger logger = Logger.getLogger(EventfulSearch.class);
    private SearchBean search;

    /**
     * Constructor takes a search bean and performs search
     * @param search Search bean
     */
    public EventfulSearch(SearchBean search) {
        this.search = search;
    }

    /**
     * Creates a String representing the URL to send to Eventful to perform search
     * @return URL in String form for Eventful search
     */
    public String constructURL() {
        String url = "http://api.eventful.com/json/events/search?";
        url += "app_key=" + eventfulKey;
        url += constructParams();
        return url;
    }

    /**
     * Creates a string of parameters to complete the eventful search
     * @return String of search params for Eventful
     */
    private String constructParams() {
        String params = "";
        if (search.getKeyword() != null) {
            params += "&keywords=" + constructSearchString(search.getKeyword());
        }

        if (search.getLocation() != null) {
            params += "&location=" + constructSearchString(search.getLocation());
        }

        if (search.getDateStart() != null && search.getDateEnd() != null) {
            params += "&date=" + constructSearchDate(search.getDateStart())
                    + "-" + constructSearchDate(search.getDateEnd());
        } else if (search.getDateStart() != null) {
            String date = constructSearchDate(search.getDateStart());
            params += "&date=" + date + "-" + date;
        } else if (search.getDateEnd() != null) {
            String date = constructSearchDate(search.getDateEnd());
            params += "&date=" + date + "-" + date;
        }

        if (search.getNumResults() != null) {
            params += "&total_items=" + search.getNumResults();
        }

        return params;
    }

    /**
     * Constructs a date for valid searching on Eventful with the format
     * YYYYMMDD00
     * @param date LocalDate representation of a date
     * @return String of that local date with the format YYYYMMDD00
     */
    private String constructSearchDate(LocalDate date) {
        String year = Integer.toString(date.getYear());
        String month = Integer.toString(date.getMonthOfYear());
        String day = Integer.toString(date.getDayOfMonth());
        return year + month + day + "00";
    }

    /**
     * Constructs a string that is safe for passing through a URL by taking out non word
     * characters and replacing them with a + sign
     * @param field Field to convert into a URL safe form
     * @return URL safe String of field
     */
    private String constructSearchString(String field) {
        String urlSafeString = "";
        for (String token : field.split("\\W")) {
            urlSafeString += token + "+";
        }
        return urlSafeString.substring(0, urlSafeString.length()-1);
    }

    /**
     * Creates a list of the event beans constructed from an Eventful search
     * @return List of event beans based on Eventful events
     */
    public List<EventBean> performSearch() throws Exception {
        List<EventBean> events = new ArrayList<>();
        String json = WebServiceUtility.callService(constructURL());
        EventsList eventfulList = WebServiceUtility.getEventfulFromJson(json);
        EventFactory factory = new EventFactory();
        for (EventItem item : eventfulList.getEvent()) {
            events.add(factory.createBean(item));
        }
        return events;
    }


}
