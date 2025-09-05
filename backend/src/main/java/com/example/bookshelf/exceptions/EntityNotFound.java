package com.example.bookshelf.exceptions;

import com.example.bookshelf.exceptions.model.EntityError;

import java.util.Collections;
import java.util.List;

public class EntityNotFound extends RuntimeException {

    private String source;

    private List<EntityError> errors;


    public EntityNotFound(String source, EntityError error) {
        super("Not found");
        this.source = source;
        this.errors = Collections.singletonList(error);
    }

    public EntityNotFound(String source, List<EntityError> errors) {
        super("Not found");
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
        return "404";
    }
}
