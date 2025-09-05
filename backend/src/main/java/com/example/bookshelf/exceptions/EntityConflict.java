package com.example.bookshelf.exceptions;

import com.example.bookshelf.exceptions.model.EntityError;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;


public class EntityConflict extends RuntimeException {

    private String source;

    private List<EntityError> errors;


    public EntityConflict(String source, EntityError error) {
        super("Conflict");
        this.source = source;
        this.errors = Collections.singletonList(error);
    }

    public EntityConflict(String source, List<EntityError> errors) {
        super("Conflict");
        this.source = source;
        this.errors = errors;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<EntityError> getErrors() {
        return errors;
    }

    public void setErrors(List<EntityError> errors) {
        this.errors = errors;
    }

    public String getStatus() {
        return "409";
    }
}
