package ru.practicum.shereit.error_handler.model.exception.not_found;

public class ItemNotFoundException extends NotFoundException {

    public ItemNotFoundException(String message) {
        super(message);
    }
}
