package ru.practicum.shereit.booking.dao.impl;

import org.springframework.context.annotation.Lazy;
import ru.practicum.shereit.booking.dao.BookingRepository;
import ru.practicum.shereit.booking.dao.BookingRepositoryCustom;
import ru.practicum.shereit.booking.model.Booking;
import ru.practicum.shereit.error_handler.model.exception.not_found.BookingNotFoundException;

public class BookingRepositoryCustomImpl implements BookingRepositoryCustom {

    private final BookingRepository bookingRepository;

    public BookingRepositoryCustomImpl(@Lazy BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking findByIdOrElseThrow(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(
                String.format("The booking with id '%d' not found", id)
        ));
    }
}
