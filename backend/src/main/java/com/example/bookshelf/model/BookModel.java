package com.example.bookshelf.model;

import com.example.bookshelf.entity.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The Book model, as it is in the JSON
 */
public class BookModel {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotNull
    private Integer year;

    @NotBlank
    private String genre;


    public BookModel(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.year = book.getYear();
        this.genre = book.getGenre();
    }

    public BookModel(Long id, String name, String author, Integer year, String genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    public BookModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return Objects.equals(id, bookModel.id) && Objects.equals(name, bookModel.name) && Objects.equals(author, bookModel.author) && Objects.equals(year, bookModel.year) && Objects.equals(genre, bookModel.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, year, genre);
    }
}
