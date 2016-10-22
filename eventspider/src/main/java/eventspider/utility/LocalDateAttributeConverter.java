package eventspider.utility;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;

/**
 * @author Sebastian Greenholtz
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {

        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date utilDate) {
        return (utilDate == null ? null : utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
