package ru.practicum.shereit.booking.dto;

import ru.practicum.shereit.booking.model.Booking;
import ru.practicum.shereit.item.mapper.ItemMapper;
import ru.practicum.shereit.user.mapper.UserMapper;

public final class BookingMapper {

    public static Booking toBooking(BookingCreateDto bookingCreateDto) {
        return Booking.builder()
                .start(bookingCreateDto.getStart())
                .end(bookingCreateDto.getEnd())
                .build();
    }

    public static BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .booker(UserMapper.toDto(booking.getBooker()))
                .item(ItemMapper.toDto(booking.getItem()))
                .status(booking.getStatus())
                .start(booking.getStart())
                .end(booking.getEnd())
                .build();
    }
}
