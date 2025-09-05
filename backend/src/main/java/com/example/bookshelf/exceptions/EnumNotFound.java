package com.example.bookshelf.exceptions;

import com.example.bookshelf.exceptions.model.EntityError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

public class EnumNotFound extends RuntimeException {

    private String source;

    private List<EntityError> errors;


    public EnumNotFound(String source, EntityError error) {
        super(String.format("Invalid option for %s, received %s", source, error.getValue()));
        this.source = source;
        this.errors = Collections.singletonList(error);
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
        return "400";
    }

    public String getTitle() {
        return "Unavailable option";
    }
}
