package testing;

import eventspider.utility.DateComparisonUtil;
import eventspider.utility.LocalDateFieldBridge;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the LocalDateFieldBridge and DateComparisonUtil
 * @author Sebastian Greenholtz
 */
public class UtilityTest {

    private DateComparisonUtil util = new DateComparisonUtil();

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

    @Test
    public void dateComparisonSuccess() {
        String start = "2016-10-31";
        String end = "2016-12-10";
        String target = "2016-11-11";
        assertTrue(util.isDateBetweenStartAndEnd(start, end, target));
    }

    @Test
    public void dateComparisonTargetEqualsStartAndEnd() {
        String start = "2016-10-31";
        String end = "2016-12-10";
        String target = "2016-10-31";
        assertTrue(util.isDateBetweenStartAndEnd(start, end, target));

        target = "2016-12-10";
        assertTrue(util.isDateBetweenStartAndEnd(start, end, target));
    }

    @Test
    public void dateComparisonFail() {
        String start = "2016-10-31";
        String end = "2016-12-10";
        String target = "2016-3-31";
        assertFalse(util.isDateBetweenStartAndEnd(start, end, target));
    }
}