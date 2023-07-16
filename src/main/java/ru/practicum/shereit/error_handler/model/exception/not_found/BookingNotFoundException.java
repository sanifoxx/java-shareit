package ru.practicum.shereit.error_handler.model.exception.not_found;

public class BookingNotFoundException extends NotFoundException {

    public BookingNotFoundException(String message) {
        super(message);
    }
}
