package testing;

import org.junit.runner.*;
import org.junit.runners.*;

/**
 * Launches the testing protocol
 * @author Sebastian Greenholtz
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        DatabaseSearchTest.class,
        EventfulSearchTest.class,
        EventHandlerTest.class,
        UtilityTest.class,
        ProfileHandlerTest.class,
        UserHandlerTest.class,
})
public class TestSuite {

}
