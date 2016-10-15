package eventspider.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * REST service applications startup, taken from <a href="https://github.com/MadJavaEntFall2016/home/blob/master/Module2/Week7/README.md">demo</a>
 * @author Paula Waite
 */
@ApplicationPath("/")
public class EventsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(EventsApplication.class );
        return h;
    }
}