package ru.practicum.shereit.error_handler.model.exception.access_is_denied;

public class AccessIsDeniedException extends RuntimeException {

    public AccessIsDeniedException(String message) {
        super(message);
    }
}
