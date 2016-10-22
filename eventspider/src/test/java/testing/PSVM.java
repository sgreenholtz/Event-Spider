package testing;

import java.util.Date;
import java.time.*;

/**
 * Simple main method testing
 * @author Sebastian Greenholtz
 */
public class PSVM {

    public static void main(String[] args) {
        Date input = new Date();
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(date);

    }
}
