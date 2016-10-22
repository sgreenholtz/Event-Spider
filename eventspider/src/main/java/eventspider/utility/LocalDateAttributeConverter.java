package eventspider.utility;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.joda.time.LocalDate;
import java.util.Date;

/**
 * Converts java.util.Date to org.joda.time.LocalDate and back
 * @author Sebastian Greenholtz
 */
@Converter
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
       return locDate.toDate();
    }

    @Override
    public LocalDate convertToEntityAttribute(Date utilDate) {
        return LocalDate.fromDateFields(utilDate);
    }
}
