package eventspider.DAL;

/**
 * Methods to facilitate the calls to Eventful API
 * @author Sebastian Greenholtz
 */
public class EventfulSearch {

    private static final String eventfulKey = "kvxN9gH3zxsQhvCF";

    /**
     * Creates a String representing the URL to send to Eventful to perform search
     * @param location Location user searched
     * @return URL in String form for Eventful search
     */
    private String constructURL(String location) {
        String url = "http://api.eventful.com/json/events/search?";
        url += "app_key=" + eventfulKey;
        url += "&location=" + constructSearchString(location);
        url += "&page_size=" + 30;
        return url;
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
