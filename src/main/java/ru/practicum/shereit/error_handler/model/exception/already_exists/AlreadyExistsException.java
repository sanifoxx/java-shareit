package ru.practicum.shereit.error_handler.model.exception.already_exists;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
