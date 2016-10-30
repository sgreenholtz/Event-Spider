package testing;

import eventspider.DAL.EventfulSearch;
import eventspider.Eventful.EventItem;
import eventspider.Eventful.EventfulResponse;
import eventspider.Eventful.EventsList;
import eventspider.beans.SearchBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import org.joda.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the EventfulSearch, which uses the <a href="http://api.eventful.com/docs/events/search">Eventful API</a>
 * @author Sebastian Greenholtz
 */
public class EventfulSearchTest {

    private static EventfulSearch search;
    private static SearchBean bean;

    @BeforeClass
    public static void setUp() throws Exception {
        bean = new SearchBean();
        bean.setNumResults(1);
    }

    private String jsonResponse(String url) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        return target.request(MediaType.APPLICATION_JSON).get(String.class);
    }

    private EventItem getItemFromJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        EventfulResponse results = mapper.readValue(json, EventfulResponse.class);
        EventsList events = results.getEvents();
        return events.getEvent().get(0);
    }

    @Test
    public void EventfulSearchTestKeywordOnly() throws Exception {
        bean.setKeyword("pizza");
        search = new EventfulSearch(bean);
        String url = search.constructURL();
        String response = jsonResponse(url);
        assertTrue(getItemFromJson(response).getDescription().contains("pizza"));
        bean.setKeyword(null);
    }

    @Test
    public void EventfulSearchTestLocationOnly() throws Exception {
        bean.setLocation("53704");
        search = new EventfulSearch(bean);
        String url = search.constructURL();
        String response = jsonResponse(url);
        assertTrue(getItemFromJson(response).getCity_name().contains("Madison"));
        bean.setLocation(null);
    }

    @Test
    public void EventfulSearchTestStartDateOnly() throws Exception {
        bean.setDateStart(new LocalDate(2016, 10, 28));
        search = new EventfulSearch(bean);
        String url = search.constructURL();
        String response = jsonResponse(url);
        assertTrue(getItemFromJson(response).getStart_time().contains(bean.getDateStart().toString()));
        bean.setDateStart(null);
    }

    @Test
    public void EventfulSearchTestEndDateOnly() throws Exception {
        bean.setDateEnd(new LocalDate(2016, 10, 28));
        search = new EventfulSearch(bean);
        String url = search.constructURL();
        String response = jsonResponse(url);
        String actual = getItemFromJson(response).getStart_time();
        String expected = bean.getDateEnd().toString();
        assertTrue(actual.contains(expected));
        bean.setDateEnd(null);
    }

}