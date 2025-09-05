package com.example.bookshelf.exceptions;

import com.example.bookshelf.exceptions.model.BookshelfApiError;
import com.example.bookshelf.exceptions.model.EntityError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * The global exception handler is used to log validation errors and standardize the API responses in case of an error
 *
 * @see BookshelfApiError
 */
@RestControllerAdvice
public class BookshelfGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BookshelfGlobalExceptionHandler.class);

    @ExceptionHandler(EnumNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BookshelfApiError handleEnumNotFoundException(EnumNotFound e) {
        BookshelfApiError apiError = new BookshelfApiError(
                e.getStatus(),
                e.getMessage(),
                e.getSource(),
                "",
                e.getErrors()
        );
        logger.error(apiError.toString());
        return apiError;
    }

    @ExceptionHandler(EntityConflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public BookshelfApiError handleEntityConflictException(EntityConflict e) {
        BookshelfApiError apiError = new BookshelfApiError(
                e.getStatus(),
                e.getMessage(),
                e.getSource(),
                "",
                e.getErrors()
        );
        logger.error(apiError.toString());
        return apiError;
    }

    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BookshelfApiError handleEntityNotFoundException(EntityNotFound e) {
        BookshelfApiError apiError = new BookshelfApiError(
                e.getStatus(),
                e.getMessage(),
                e.getSource(),
                "",
                e.getErrors()
        );
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
    public BookshelfApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<EntityError> errors = ex.getBindingResult().getFieldErrors().stream().map(error ->
                new EntityError(error.getField(), null, error.getDefaultMessage())).toList();
        BookshelfApiError apiError = new BookshelfApiError("400", "Bad Request", null, ex.getMessage(), errors);
        logger.error(apiError.toString());
        return apiError;
    }
}
