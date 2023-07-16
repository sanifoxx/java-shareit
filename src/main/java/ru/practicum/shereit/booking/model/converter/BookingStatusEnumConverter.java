package ru.practicum.shereit.booking.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.practicum.shereit.booking.model.BookingStatus;

@Component
public class BookingStatusEnumConverter implements Converter<String, BookingStatus> {

    @Override
    public BookingStatus convert(String s) {
        return BookingStatus.valueOf(s.toUpperCase());
    }
}
