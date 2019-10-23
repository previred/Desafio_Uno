package cl.previred.challenge.periods.api.serializer;

import cl.previred.challenge.periods.parser.CalendarParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

/**
 * @author Luis Riveros - luis.riveros_ex@scotiabank.cl
 * @version 1.0.0 - 22-10-2019
 * @since 1.0.0 - 22-10-2019
 */
public class CalendarDeserialize extends StdDeserializer<Calendar> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarDeserialize.class);

    public CalendarDeserialize() {
        super(Calendar.class);
    }

    @Override
    public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return CalendarParser.build().parse(p.getValueAsString());
        } catch (ParseException pe) {
            LOGGER.error("Error data Deserialize", pe);
        }
        return null;
    }
}
