package ru.practicum.shereit.error_handler.model.exception.access_is_denied;

public class BookingAccessIsDeniedException extends AccessIsDeniedException {

    public BookingAccessIsDeniedException(String message) {
        super(message);
    }
}
