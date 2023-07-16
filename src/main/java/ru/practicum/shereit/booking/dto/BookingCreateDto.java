package ru.practicum.shereit.booking.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shereit.booking.model.annotation.ValidBookingTime;

import java.time.LocalDateTime;

@Data
@Builder
@ValidBookingTime(message = "Incorrect start or end time")
public class BookingCreateDto {

    private Long bookerId;

    @NotNull(message = "The 'itemId' field must not be null")
    private Long itemId;

    private LocalDateTime start;

    private LocalDateTime end;
}
