package eventsbatching;

/**
 * @author Sebastian Greenholtz
 */
public class EventsTester {

    public static void main(String[] args) {
        JSONHandler handler = new JSONHandler("static_eventful.json");
        System.out.println(handler.getFieldFromJSON("events"));
    }
}
