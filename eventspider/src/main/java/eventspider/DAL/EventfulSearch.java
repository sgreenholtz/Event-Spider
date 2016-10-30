package eventspider.DAL;

import eventspider.beans.SearchBean;
import org.joda.time.LocalDate;

/**
 * Methods to facilitate the calls to Eventful API
 * @author Sebastian Greenholtz
 */
public class EventfulSearch {

    private static final String eventfulKey = "sBbJgh96n2W9JV4m";

    /**
     * Creates a String representing the URL to send to Eventful to perform search
     * @param search Search bean
     * @return URL in String form for Eventful search
     */
    public String constructURL(SearchBean search) {
        String url = "http://api.eventful.com/json/events/search?";
        url += "app_key=" + eventfulKey;
        url += constructParams(search);
        return url;
    }

    /**
     * Creates a string of parameters to complete the eventful search
     * @param search Search Bean
     * @return String of search params for Eventful
     */
    private String constructParams(SearchBean search) {
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
}
