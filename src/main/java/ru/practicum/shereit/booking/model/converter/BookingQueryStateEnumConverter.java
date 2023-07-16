package ru.practicum.shereit.booking.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.practicum.shereit.booking.controller.BookingQueryState;

@Component
public class BookingQueryStateEnumConverter implements Converter<String, BookingQueryState>{

    @Override
    public BookingQueryState convert(String source) {
        return BookingQueryState.valueOf(source.toUpperCase());
    }
}
