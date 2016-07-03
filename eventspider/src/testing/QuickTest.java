package testing;

import java.text.*;
import java.util.*;

/**
 * Quick testing in command line
 * @author Sebastian Greenholtz
 */
public class QuickTest {

    public static void main(String[] args) throws ParseException {
        String date = "2016-06-25 08:00:00.0";
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date dateObjectRepresentation = dateTimeFormatter.parse(date);
        dateTimeFormatter = new SimpleDateFormat("EEE, MMM d h:mm a");
        String newDate = dateTimeFormatter.format(dateObjectRepresentation);
        System.out.println(newDate);

    }
}

