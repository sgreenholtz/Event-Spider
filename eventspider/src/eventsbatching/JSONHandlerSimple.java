package eventsbatching;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Handles JSON requests
 * @author Sebastian Greenholtz
 */
public class JSONHandlerSimple {

    private String JSONFilePath;
    private JSONObject jsonObject;

    /**
     * Empty constructor
     */
    public JSONHandlerSimple() {}

    /**
     * Constructor assigns the filePath to the JSON file
     * path variable
     * @param filePath Path to a JSON file
     */
    public JSONHandlerSimple(String filePath) {
        JSONFilePath = filePath;
        this.jsonObject = createJsonFromFile();
    }

    /**
     * Utility class to create a JSONObject from the file
     * @return JSONObject of the JSON file
     */
    private JSONObject createJsonFromFile() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(JSONFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Gets a single field from the JSON
     * @param field Field name to retrieve
     * @return String of the value associated with the field name
     */
    public String getFieldFromJSON(String field) {
        return jsonObject.get(field).toString();
    }

    /**
     * Gets a JSONArray from a field that represents a JSONArray
     * @param arrayField Field name that is an array
     * @return JSONArray of the given field
     */
    public JSONArray getJSONArrayFromJSON(String arrayField) {
        return (JSONArray) jsonObject.get(arrayField);
    }

    /**
     * Get a JSONArray from a JSON object that is not the default JSON object
     * @param object JSON object to get field
     * @param arrayField Field that represents the array
     * @return JSONArray
     */
    public JSONArray getJSONArrayFromJSON(JSONObject object, String arrayField) {
        return (JSONArray) object.get(arrayField);
    }

    /**
     * Gets a JSON object with the given name
     * @param objectField Field that contains a JSON object
     * @return JSONObject of given field
     */
    public JSONObject getJSONObjectFromJSON(String objectField) {
        return (JSONObject) jsonObject.get(objectField);
    }
}
