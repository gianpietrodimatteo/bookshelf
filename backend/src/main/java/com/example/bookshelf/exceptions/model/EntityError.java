package com.example.bookshelf.exceptions.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityError {

    private String field;

    private String value;

    private String message;


    public EntityError(String field, String value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public EntityError() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntityError that = (EntityError) o;
        return Objects.equals(field, that.field) && Objects.equals(value, that.value) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, value, message);
    }

    @Override
    public String toString() {
        return "EntityError{" +
                "field='" + field + '\'' +
                ", value='" + value + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
