package eventsbatching;

import beans.EventBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import beans.EventFactory;
import java.util.*;

/**
 * Handles the JSON parsing for the Eventful API
 * @author Sebastian Greenholtz
 */
public class EventfulParser extends JSONHandlerSimple {

    private ArrayList<JSONObject> eventsList;
    private Map<String, EventBean> eventMap;

    /**
     * Empty constructor to instantiate map and list
     */
    public EventfulParser() {
    }

    /**
     * Constructor takes in the path to the JSON file and
     * calls the constructor for the JSONHandler
     * @param jsonPath Path to a JSON file
     */
    public EventfulParser(String jsonPath) {
        super(jsonPath);
        eventsList = new ArrayList<>();
        eventMap = new HashMap<>();
        createEventArrayList();
        createEventsMap();
    }

    /**
     * Gets the event map.
     * @return eventMap
     */
    public Map<String, EventBean> getEventMap() {
        return eventMap;
    }

    /**
     * Uses the EventFactory class to create Event beans from each of the events
     * in the Event Array list, then add them to a map of ID -> EventBean.
     * Sets  the eventMap instance variable based on this updated map.
     */
    private void createEventsMap() {
        EventFactory factory = new EventFactory();
        for (JSONObject event : eventsList) {
            EventBean bean = factory.createBean(super.getFieldFromJSON(event, "id"),
                                super.getFieldFromJSON(event, "title"),
                                super.getFieldFromJSON(event, "url"),
                                super.getFieldFromJSON(event, "description"),
                                (super.getFieldFromJSON(event, "start_time")),
                                "",
                                super.getFieldFromJSON(event, "venue_address"),
                                super.getFieldFromJSON(event, "city_name"),
                                super.getFieldFromJSON(event, "region_abbr"),
                                super.getFieldFromJSON(event, "postal_code")
                                );
            factory.updateBeansMap(bean);
        }
        eventMap = factory.getEventMap();
    }

    /**
     * Adds events to the instance variable Arraylist of JSONObjects representing
     * each event in the Event list
     */
    private void createEventArrayList() {
        JSONArray events = getEventArray();
        Iterator<JSONObject> iterator = events.iterator();
        while (iterator.hasNext()) {
            eventsList.add(iterator.next());
        }
    }

    /**
     * Gets the array of Event objects by getting the "events" object, then getting
     * the array for the name "event"
     * @return JSONArray of Event objects
     */
    private JSONArray getEventArray() {
        return super.getJSONArrayFromJSON(super.getJSONObjectFromJSON("events"), "event");
    }


}
