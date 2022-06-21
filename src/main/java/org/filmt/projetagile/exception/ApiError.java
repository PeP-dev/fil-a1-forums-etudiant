package org.filmt.projetagile.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class ApiError {
    private final HttpStatus status;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    private final String path;
    public ApiError(HttpStatus status, String message, String path) {
        timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
