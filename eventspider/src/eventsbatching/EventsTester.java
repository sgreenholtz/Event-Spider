package eventsbatching;

/**
 * @author Sebastian Greenholtz
 */
public class EventsTester {

    public static void main(String[] args) {
        JSONHandlerSimple handler = new JSONHandlerSimple("static_eventful.json");
        System.out.println(handler.getFieldFromJSON("events"));
    }
}
