package ru.practicum.shereit.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingUpdateDto {

    private final Long id;
    private final Long ownerId;
    private final Boolean approved;
}
