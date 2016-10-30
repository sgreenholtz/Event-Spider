package eventspider.DAL;

import eventspider.beans.EventBean;
import eventspider.beans.SearchBean;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Methods to facilitate the calls to Eventful API
 * @author Sebastian Greenholtz
 */
public class EventfulSearch {

    private static final String eventfulKey = "sBbJgh96n2W9JV4m";
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
    public List<EventBean> performSearch() {
        List<EventBean> events = new ArrayList<>();
        //TODO: Add code to do the rest call and parse the result
        return events;
    }
}
