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

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("eventspider/src/eventsbatching/sample.json"));
            String pageSize = (String) jsonObject.get("page_size");
            System.out.println(pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
