package eventspider.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import eventspider.Eventful.EventfulResponse;
import eventspider.Eventful.EventsList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Utility for calling and parsing web services
 * @author Sebastian Greenholtz
 */
public class WebServiceUtility {
    /**
     * Calls the web service at string URL
     * @param url URL from which to call the serviec
     * @return JSON response as a string
     * @throws Exception
     */
    public static String callService(String url) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        return target.request(MediaType.APPLICATION_JSON).get(String.class);
    }

    /**
     * Parses the JSON response from Eventful and returns the list
     * @param json String of JSON data to parse from Eventful
     * @return EventsList object from JSON
     * @throws Exception
     */
    public static EventsList getEventfulFromJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        EventfulResponse results = mapper.readValue(json, EventfulResponse.class);
        return results.getEvents();
    }
}
