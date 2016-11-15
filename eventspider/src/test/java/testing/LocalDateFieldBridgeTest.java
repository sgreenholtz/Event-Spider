package testing;

import eventspider.utility.LocalDateFieldBridge;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the LocalDateFieldBridge
 * @author Sebastian Greenholtz
 */
public class LocalDateFieldBridgeTest {

    @Test
    public void objectToStringTest() {
        LocalDate date = new LocalDate(2016, 11, 13);
        LocalDateFieldBridge bridge = new LocalDateFieldBridge();
        String result = bridge.objectToString(date);
        String expected = "20161113";
        assertTrue("Incorrect date format", result.equals(expected));
    }

    @Test(expected = ClassCastException.class)
    public void objectToStringIncorrectDatatypeTest() {
        String date = "20161113";
        LocalDateFieldBridge bridge = new LocalDateFieldBridge();
        String result = bridge.objectToString(date);
    }

}