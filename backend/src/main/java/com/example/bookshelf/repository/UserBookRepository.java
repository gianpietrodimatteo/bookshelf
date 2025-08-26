package com.example.bookshelf.repository;

import com.example.bookshelf.entity.Book;
import com.example.bookshelf.entity.User;
import com.example.bookshelf.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The user-book repository interface
 *
 * @see com.example.bookshelf.service.UserBookService
 */
public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    /**
     * Queries the user's book list by the user
     *
     * @param user the user entity (must have id)
     * @return a list with the user's books entities
     */
    List<UserBook> findByUser(User user);

    /**
     * Checks if an association between a user and a book exist. Cheap operation
     *
     * @param userId the user's id
     * @param bookId the book's id
     * @return true if it exists, false if it does not
     */
    boolean existsByUserIdAndBookId(Long userId, Long bookId);

    /**
     * Finds by user and book.
     * There is a composite unique constraint for user and book, so there should be at most one result
     *
     * @param user the user
     * @param book the book
     * @return an optional containing (or not) the user-book association entity
     */
    Optional<UserBook> findByUserAndBook(User user, Book book);
}
