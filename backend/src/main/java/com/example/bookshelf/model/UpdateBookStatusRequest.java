package com.example.bookshelf.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * The JSON with the request for updating the UserBook's status
 * The string must be a valid representation of the BookStatus enum
 *
 * @see com.example.bookshelf.enums.BookStatus
 */
public class UpdateBookStatusRequest {

    @NotBlank
    @Size(max = 50)
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdateBookStatusRequest that = (UpdateBookStatusRequest) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }
}
