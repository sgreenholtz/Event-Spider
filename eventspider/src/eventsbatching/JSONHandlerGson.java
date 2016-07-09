package eventsbatching;

import com.google.gson.*;

/**
 * Handles JSON processing using Gson library
 * @author Sebastian Greenholtz
 */
public class JSONHandlerGson {

    private String jsonFilePath;

    /**
     * Constructor assigns the path to the JSON file
     * @param path Path to JSON file
     */
    public JSONHandlerGson(String path) {
        jsonFilePath = path;
    }

    public void getObject() {
        Gson gson = new Gson();

    }
}
