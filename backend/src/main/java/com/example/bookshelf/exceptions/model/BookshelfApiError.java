package com.example.bookshelf.exceptions.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * The default JSON response and log entry for when a known exception occurs, with a timestamp and details on what happened
 */
public class BookshelfApiError {

    /**
     * The time the exception was caught
     */
    private LocalDateTime timestamp;

    /**
     * The HTTP status code
     */
    private HttpStatus status;

    /**
     * The error message associated with the exception
     */
    private String message;

    /**
     * List of constructed error messages
     */
    private List<String> errors;


    public BookshelfApiError(HttpStatus status, String message, String error) {
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }

    public BookshelfApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public BookshelfApiError() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "BookshelfApiError{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
