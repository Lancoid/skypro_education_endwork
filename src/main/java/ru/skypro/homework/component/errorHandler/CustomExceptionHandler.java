package ru.skypro.homework.component.errorHandler;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.skypro.homework.dto.ErrorDto;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static void logException(Exception exception) {
        log.error(exception.getMessage());

        if (log.isTraceEnabled()) {
            exception.printStackTrace();
        }
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NotNull Exception exception,
            Object body,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus httpStatus,
            WebRequest request
    ) {
        logException(exception);

        return ResponseEntity.status(httpStatus)
                .body(Optional.ofNullable(body)
                        .orElse(ErrorDto.of(httpStatus, exception.getMessage(),
                                request.getDescription(false)))
                );
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        String message = exception.getFieldErrors().stream()
                .map(fieldError -> String.format("%s (%s)", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining("; ", "Неправильный аргумент метода: ", ""));

        return handleException(exception, HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException exception, WebRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler({EntityNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException exception, WebRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception, WebRequest request) {
        return handleException(exception, HttpStatus.FORBIDDEN, exception.getMessage(), request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        return handleException(exception, HttpStatus.FORBIDDEN, exception.getMessage(), request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request) {
        return handleException(exception, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), request);
    }

    private ResponseEntity<Object> handleException(
            Exception exception,
            HttpStatus httpStatus,
            String message,
            WebRequest request
    ) {
        return handleExceptionInternal(
                exception,
                ErrorDto.of(httpStatus, message, request.getDescription(false)),
                HttpHeaders.EMPTY,
                httpStatus,
                request
        );
    }

}
