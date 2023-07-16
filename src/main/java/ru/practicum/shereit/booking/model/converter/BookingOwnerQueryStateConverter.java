package ru.practicum.shereit.booking.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.practicum.shereit.booking.controller.BookingOwnerQueryState;

@Component
public class BookingOwnerQueryStateConverter implements Converter<String, BookingOwnerQueryState> {

    @Override
    public BookingOwnerQueryState convert(String source) {
        return BookingOwnerQueryState.valueOf(source.toUpperCase());
    }
}
