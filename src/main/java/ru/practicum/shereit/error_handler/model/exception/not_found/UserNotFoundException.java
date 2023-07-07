package ru.practicum.shereit.error_handler.model.exception.not_found;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
