package com.example.bookshelf.exceptions;

public class EntityNotFound extends RuntimeException {

    public EntityNotFound(String entityClassName, Long id) {
        super(String.format("%s of id %d not found.", entityClassName, id));
    }

    public EntityNotFound(String message) {
        super(message);
    }
}
