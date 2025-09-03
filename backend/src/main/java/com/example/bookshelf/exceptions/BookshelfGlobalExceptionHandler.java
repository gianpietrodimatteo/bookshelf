package com.example.bookshelf.exceptions;

import com.example.bookshelf.exceptions.model.BookshelfApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The global exception handler is used to log validation errors and standardize the API responses in case of an error
 *
 * @see BookshelfApiError
 */
@RestControllerAdvice
public class BookshelfGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BookshelfGlobalExceptionHandler.class);


    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BookshelfApiError handleEntityNotFoundException(RuntimeException e) {
        BookshelfApiError apiError = new BookshelfApiError();
        apiError.setMessage(e.getMessage());
        apiError.setTimestamp(LocalDateTime.now());
        logger.error(apiError.toString());
        return apiError;
    }

    /**
     * Handles validation exceptions and provides API response
     *
     * @param ex the MethodArgumentNotValidException we're catching and treating
     * @return an object with details on what validation failures occurred
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookshelfApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();
        BookshelfApiError apiError = new BookshelfApiError(
                HttpStatus.BAD_REQUEST,
                "Validation failure",
                errors
        );
        logger.error(apiError.toString());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
