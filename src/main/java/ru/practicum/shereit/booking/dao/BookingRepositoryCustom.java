package ru.practicum.shereit.booking.dao;

import ru.practicum.shereit.booking.model.Booking;

public interface BookingRepositoryCustom {

    Booking findByIdOrElseThrow(Long id);
}
