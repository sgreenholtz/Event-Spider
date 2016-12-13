package eventspider.utility;

import org.joda.time.*;
import org.joda.*;

import java.util.ArrayList;

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
     * @return bool if target is between start and end, inclusive
     */
    public boolean isDateBetweenStartAndEnd(String start, String end, String target) {
        LocalDate startLD = parseDate(start);
        LocalDate endLD = parseDate(end);
        LocalDate targetLD = parseDate(target);
        int isAfterStart = targetLD.compareTo(startLD); // if true, returns positive
        int isBeforeEnd = targetLD.compareTo(endLD); // if true, returns negative
        return (isAfterStart * isBeforeEnd <= 0);
    }

    /**
     * Tests whether a given date after another date. Dates are in string format,
     * YYYY-MM-DD HH:MM:SS or YYYY-MM-DD. Uses Joda Time, parses String dates into Local Dates,
     * and compares target to start date.
     * @param start String date marking the beginning of the range
     * @param target Target date
     * @return true if target is after or the same as start
     */
    public boolean isAfterStartDate(String start, String target) {
        LocalDate startLD = parseDate(start);
        LocalDate targetLD = parseDate(target);
        int isAfterStart = targetLD.compareTo(startLD); // if true, returns positive
        return (isAfterStart >= 0);
    }

    /**
     * Tests whether a given date before another date. Dates are in string format,
     * YYYY-MM-DD HH:MM:SS or YYYY-MM-DD. Uses Joda Time, parses String dates into Local Dates,
     * and compares target to end date.
     * @param end End date marking the end of the range
     * @param target Target date
     * @return true if target is before or the same as end
     */
    public boolean isBeforeEndDate(String end, String target) {
        LocalDate endLD = parseDate(end);
        LocalDate targetLD = parseDate(target);
        int isBeforeEnd = targetLD.compareTo(endLD); // if true, returns negative
        return (isBeforeEnd <= 0);
    }

    /**
     * Parses string to date for any format of year, month, day with
     * dash delimiter or slash
     * @param strDate String of date
     * @return LocalDate based on string
     */
    private LocalDate parseDate(String strDate) {
        String[] arr = strDate.split("[\\-\\s\\:]");
        ArrayList<Integer> date = new ArrayList<>();
        int i=0;
        while (date.size()<3) {
            if (arr[i].matches("\\d+")) {
                date.add(Integer.parseInt(arr[i]));
            }
            i++;
        }
        return new LocalDate(date.get(0), date.get(1), date.get(2));
    }
}
