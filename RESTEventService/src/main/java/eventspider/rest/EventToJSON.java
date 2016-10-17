package eventspider.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import eventspider.beans.EventBean;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * Translates the Event into a JSON object
 * @author Sebastian Greenholtz
 */
public class EventToJSON {

    private static final Logger logger = Logger.getLogger(EventToJSON.class);

    public static String parse(EventBean event) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(event);
        } catch (IOException e) {
            logger.error(e);
        }
        return json;
    }

    public static String parse(List<EventBean> events) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(events);
        } catch (Exception e) {
            logger.error(e);
        }
        return json;
    }
}
