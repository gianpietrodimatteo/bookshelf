package com.example.bookshelf.model;

import com.example.bookshelf.entity.UserBook;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * The UserBook model (user and book association), as it is in the JSON
 */
public class UserBookModel {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private BookModel book;

    @Size(max = 50)
    @NotBlank
    private String status;


    public UserBookModel(UserBook userBook) {
        this.id = userBook.getId();
        this.book = new BookModel(userBook.getBook());
        this.status = userBook.getStatus().toString();
    }

    public UserBookModel(Long id, BookModel book, String status) {
        this.id = id;
        this.book = book;
        this.status = status;
    }

    public UserBookModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserBookModel that = (UserBookModel) o;
        return Objects.equals(id, that.id) && Objects.equals(book, that.book) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, status);
    }
}
