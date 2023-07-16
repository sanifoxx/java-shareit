package ru.practicum.shereit.booking.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shereit.item.dto.ItemDto;
import ru.practicum.shereit.booking.model.BookingStatus;
import ru.practicum.shereit.user.dto.UserDto;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingDto {

    private Long id;

    private UserDto booker;

    private ItemDto item;

    private BookingStatus status;

    private LocalDateTime start;

    private LocalDateTime end;
}
