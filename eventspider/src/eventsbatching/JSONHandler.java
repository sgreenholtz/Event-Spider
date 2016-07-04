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
public class JSONHandler {

    private String JSONFilePath;

    /**
     * Constructor assigns the filePath to the JSON file
     * path variable
     * @param filePath Path to a JSON file
     */
    public JSONHandler(String filePath) {
        JSONFilePath = filePath;
    }

    /**
     * Gets a single field from the JSON
     * @param field Field name to retrieve
     * @return String of the value associated with the field name
     */
    public String getFieldFromJSON(String field) {
        JSONParser parser = new JSONParser();
        String jsonValue = "";
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(JSONFilePath));
            jsonValue = jsonObject.get(field).toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonValue;
    }
}
