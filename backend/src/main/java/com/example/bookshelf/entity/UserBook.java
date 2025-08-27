package com.example.bookshelf.entity;

import com.example.bookshelf.enums.BookStatus;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * The UserBook entity, as it is in the database
 */
@Entity
@Table(name = "user_book", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "book_id"})})
public class UserBook {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private BookStatus status;


    public UserBook() {
        this.status = BookStatus.WISHLISTED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserBook userBook = (UserBook) o;
        return Objects.equals(id, userBook.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
