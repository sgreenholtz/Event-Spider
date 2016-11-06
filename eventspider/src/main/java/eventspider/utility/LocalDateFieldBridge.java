package eventspider.utility;

import org.hibernate.search.bridge.StringBridge;
import org.joda.time.LocalDate;

/**
 * Field Bridge to handle org.joda.time.LocalDate String format is yyyyMMdd, to
 * match documentation for DateBridge.
 * @author Sebastian Greenholtz
 */
public class LocalDateFieldBridge implements StringBridge {

    @Override
    public String objectToString(Object o) {
        LocalDate localDate = (LocalDate) o;
        return localDate.toString("yyyyMMdd");
    }
}
