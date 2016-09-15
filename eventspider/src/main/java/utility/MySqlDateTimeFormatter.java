package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Format to and from MySql format
 * @author Sebastian Greenholtz
 */
public final class MySqlDateTimeFormatter {

    /**
     * Formats Date/Time from the human readable EEE, MM d h:mm a to the
     * MySql format yyy-MM-dd hh:mm:ss.S
     * @param dateTime Date/Time in unformatted, human readable style
     * @return MySql formatted Date/Time
     */
    public static String formatToMysqlFromStandard(String dateTime) throws Exception{
        SimpleDateFormat inputFormatter = new SimpleDateFormat("EEEE, MMM d h:mm a");
        Date dateObjectRepresentation = inputFormatter.parse(dateTime);
        SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        dateTime = outputFormatter.format(dateObjectRepresentation);
        return dateTime;
    }

    /**
     * Creates a single string representing the datetime value in format for MySql:
     * YYYY-MM-DD HH:MM:SS
     * @param date String of the date from form
     * @param time String of the time from form
     * @return String of datetime
     */
    public static String formatToMysqlFromForm(String date, String time) {
        String dateTime = "";
        dateTime += date + " " + time + ":00";
        return dateTime;
    }
}
