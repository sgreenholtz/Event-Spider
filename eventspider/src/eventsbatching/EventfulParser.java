package eventsbatching;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * Handles the JSON parsing for the Eventful API
 * @author Sebastian Greenholtz
 */
public class EventfulParser extends JSONHandlerSimple {

    /**
     * Constructor takes in the path to the JSON file and
     * calls the constructor for the JSONHandler
     * @param jsonPath Path to a JSON file
     */
    public EventfulParser(String jsonPath) {
        super(jsonPath);
    }

    public void createEventArrayList() {
        ArrayList<JSONObject> eventsList = new ArrayList<>();
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
