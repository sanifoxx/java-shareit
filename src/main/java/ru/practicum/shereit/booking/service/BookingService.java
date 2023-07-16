package ru.practicum.shereit.booking.service;

import ru.practicum.shereit.booking.controller.BookingOwnerQueryState;
import ru.practicum.shereit.booking.controller.BookingQueryState;
import ru.practicum.shereit.booking.dto.BookingCreateDto;
import ru.practicum.shereit.booking.dto.BookingDto;
import ru.practicum.shereit.booking.dto.BookingUpdateDto;

import java.util.List;

public interface BookingService {

    BookingDto createNewBooking(BookingCreateDto bookingCreateDto);

    BookingDto getBookingById(Long bookingId, Long userId);

    List<BookingDto> getAllBookingsForCurrentUser(Long userId, BookingQueryState state);

    List<BookingDto> getAllBookingsForOwnerUser(Long userId, BookingOwnerQueryState state);

    BookingDto updateBookingStatus(BookingUpdateDto bookingUpdateDto);
}
