package org.lanadvice.serializers;

import javax.ws.rs.ext.ParamConverter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParameterConverter implements ParamConverter<LocalDateTime> {

    public static final String DEFAULT_FORMAT = DateTimeFormat.DEFAULT_DATE_TIME;

    private DateTimeFormat customDateTimeFormat;
    private DateFormat customDateFormat;

    public void setCustomDateFormat(DateFormat customDateFormat) {
        this.customDateFormat = customDateFormat;
    }

    public void setCustomDateTimeFormat(DateTimeFormat
                                                customDateTimeFormat) {
        this.customDateTimeFormat = customDateTimeFormat;
    }

    @Override
    public LocalDateTime fromString(String string) {
        String format = DEFAULT_FORMAT;
        if (customDateFormat != null) {
            format = customDateFormat.value();
        } else if (customDateTimeFormat != null) {
            format = customDateTimeFormat.value();
        }

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return LocalDateTime.parse(string, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString(LocalDateTime date) {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(date);
    }
}
