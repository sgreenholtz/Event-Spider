package testing;

import org.joda.time.*;

/**
 * Simple main method testing
 * @author Sebastian Greenholtz
 */
public class PSVM {

    public static void main(String[] args) {
        LocalDate localDate = new LocalDate(2016,10, 22);
        String date = localDate.toString("yyyyMMdd");
        System.out.println(date);

    }
}
