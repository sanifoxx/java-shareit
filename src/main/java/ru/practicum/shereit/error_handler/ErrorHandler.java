package ru.practicum.shereit.error_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shereit.error_handler.model.exception.access_is_denied.AccessIsDeniedException;
import ru.practicum.shereit.error_handler.model.exception.already_exists.AlreadyExistsException;
import ru.practicum.shereit.error_handler.model.exception.not_found.NotFoundException;
import ru.practicum.shereit.error_handler.model.ErrorResponse;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.debug("ERROR: MethodArgumentNotValidException | {}",
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage()
        );
        return new ErrorResponse(
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage()
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
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAlreadyExistsException(final AlreadyExistsException e) {
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
