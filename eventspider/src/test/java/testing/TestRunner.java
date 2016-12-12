package testing;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.apache.log4j.*;

/**
 * Runs the test suite
 * @author Sebastian Greenholtz
 */
public class TestRunner {

    private static final Logger logger = Logger.getLogger(TestRunner.class);

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);

        for (Failure failure : result.getFailures()) {
            logger.info(failure.toString());
        }

        logger.info(result.wasSuccessful());
    }
}
