package eventsbatching;

/**
 * @author Sebastian Greenholtz
 */
public class EventsTester {

    public static void main(String[] args) {
        EventfulParser parser = new EventfulParser("static_eventful.json");
        System.out.println(parser.getEventMap());
    }


}
