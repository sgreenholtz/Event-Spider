package eventsbatching;

/**
 * @author Sebastian Greenholtz
 */
public class EventsTester {

    public static void main(String[] args) {
        EventfulParser parser = new EventfulParser("/Applications/tomcat/temp/1_eventful.json");
        System.out.println(parser.getEventMap());
    }

}
