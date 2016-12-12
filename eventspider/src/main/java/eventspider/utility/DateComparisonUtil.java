package eventspider.utility;

import org.joda.time.*;
import org.joda.*;

/**
 * Utility class to compare dates in various formats
 * @author Sebastian Greenholtz
 */
public class DateComparisonUtil {

    /**
     * Tests whether a given date is between two other dates. Dates are in string format,
     * YYYY-MM-DD HH:MM:SS or YYYY-MM-DD. Uses Joda Time, parses String dates into Local Dates,
     * and compares target to start and end dates.
     * @param start String start date of the range
     * @param end String end date of the range
     * @param target String date to test if within the range
     * @return bool if target is between start and end
     */
    public boolean isDateBetweenStartAndEnd(String start, String end, String target) {
        LocalDate startLD = LocalDate.parse(start);
        LocalDate endLD = LocalDate.parse(end);
        LocalDate targetLD = LocalDate.parse(target);
        int isAfterStart = targetLD.compareTo(startLD); // if true, returns positive
        int isBeforeEnd = targetLD.compareTo(endLD); // if true, returns negative
        return (isAfterStart * isBeforeEnd <= 0);
    }
}
