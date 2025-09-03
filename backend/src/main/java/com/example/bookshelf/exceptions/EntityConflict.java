package com.example.bookshelf.exceptions;

import com.example.bookshelf.exceptions.model.EntityError;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class EntityConflict extends RuntimeException {

    public EntityConflict(String entityClassName, Set<EntityError> entityErrors) {
        super(String.format("%s conflict. Existing entry with %s ", entityClassName,
                entityErrors.stream().map(
                        EntityError::toString).collect(joining(" and "))));
    }
}
