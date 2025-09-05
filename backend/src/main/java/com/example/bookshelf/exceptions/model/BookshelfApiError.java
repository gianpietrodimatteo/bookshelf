package com.example.bookshelf.exceptions.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookshelfApiError {

    private String status;

    private String title;

    private String source;

    private String detail;

    private List<EntityError> errors;

    public BookshelfApiError(String status, String title, String source, String detail, EntityError error) {
        this.status = status;
        this.title = title;
        this.source = source;
        this.detail = detail;
        this.errors = Collections.singletonList(error);
    }

    public BookshelfApiError(String status, String title, String source, String detail, List<EntityError> errors) {
        this.status = status;
        this.title = title;
        this.source = source;
        this.detail = detail;
        this.errors = errors;
    }

    public BookshelfApiError() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<EntityError> getErrors() {
        return errors;
    }

    public void setErrors(List<EntityError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "BookshelfApiError{" +
                "status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", detail='" + detail + '\'' +
                ", errors=" + errors +
                '}';
    }
}
