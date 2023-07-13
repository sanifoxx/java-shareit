package ru.practicum.shereit.error_handler.model.exception.not_found;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
