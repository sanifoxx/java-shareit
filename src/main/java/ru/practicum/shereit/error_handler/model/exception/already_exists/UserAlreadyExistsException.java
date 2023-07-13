package ru.practicum.shereit.error_handler.model.exception.already_exists;

public class UserAlreadyExistsException extends AlreadyExistsException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
