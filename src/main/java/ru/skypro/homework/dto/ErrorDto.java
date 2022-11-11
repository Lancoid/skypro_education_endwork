package ru.skypro.homework.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorDto {

    private final int statusCode;
    private final LocalDateTime timestamp;
    private final String message;
    private final String description;

    private ErrorDto(HttpStatus httpStatus, String message, String description) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = httpStatus.value();
        this.message = message;
        this.description = description;
    }

    public static ErrorDto of(HttpStatus httpStatus, String message, String description) {
        return new ErrorDto(httpStatus, message, description);
    }
}