package ru.practicum.shereit.error_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.practicum.shereit.error_handler.model.exception.access_is_denied.AccessIsDeniedException;
import ru.practicum.shereit.error_handler.model.exception.access_is_denied.BookingAccessIsDeniedException;
import ru.practicum.shereit.error_handler.model.exception.already_exists.AlreadyExistsException;
import ru.practicum.shereit.error_handler.model.exception.unavailable.ItemUnavailableException;
import ru.practicum.shereit.error_handler.model.exception.not_found.NotFoundException;
import ru.practicum.shereit.error_handler.model.ErrorResponse;
import ru.practicum.shereit.error_handler.model.exception.unavailable.UnavailableException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    //400
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        log.debug("ERROR: MethodArgumentTypeMismatchException | {}", e.getValue());
        return new ErrorResponse(
                String.format("Unknown state: %s", e.getValue())
        );
    }

    //400
    @ExceptionHandler({BookingAccessIsDeniedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBookingAccessIsDeniedException(final BookingAccessIsDeniedException e) {
        log.debug("ERROR: BookingAccessIsDeniedException | {}", e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        String message = e.getFieldError() == null
                ? e.getGlobalError() == null
                    ? e.toString()
                    : e.getGlobalError().getDefaultMessage()
                : e.getFieldError().getDefaultMessage();
        log.debug("ERROR: MethodArgumentNotValidException | {}", message);
        return new ErrorResponse(
                message
        );
    }

    //400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUnavailableException(final UnavailableException e) {
        log.debug("ERROR: UnavailableException | {}", e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingRequestHeaderException(final MissingRequestHeaderException e) {
        log.debug("ERROR: MissingRequestHeaderException | {}", e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    // 403
    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessIsDeniedException(final AccessIsDeniedException e) {
        log.debug("ERROR AccessIsDeniedException | {}", e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    // 404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        log.debug("ERROR: NotFoundException | {}", e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    // 409
    @ExceptionHandler({AlreadyExistsException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAlreadyExistsException(final RuntimeException e) {
        log.debug("ERROR: AlreadyExistsException | {}", e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    // 500
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(final RuntimeException e) {
        log.debug("ERROR: RuntimeException | {}", e.getMessage());
        e.printStackTrace();
        return new ErrorResponse(
                e.getMessage()
        );
    }
}
