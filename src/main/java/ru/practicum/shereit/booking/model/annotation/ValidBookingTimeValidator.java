package ru.practicum.shereit.booking.model.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.practicum.shereit.booking.dto.BookingCreateDto;

import java.time.LocalDateTime;

public class ValidBookingTimeValidator implements ConstraintValidator<ValidBookingTime, BookingCreateDto> {

    @Override
    public boolean isValid(BookingCreateDto bookingCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime start = bookingCreateDto.getStart();
        LocalDateTime end = bookingCreateDto.getEnd();
        return start != null && end != null
                && end.isAfter(LocalDateTime.now())
                && start.isAfter(LocalDateTime.now())
                && start.isBefore(end);
    }
}
