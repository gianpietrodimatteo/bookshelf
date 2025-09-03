package com.example.bookshelf.service;

import com.example.bookshelf.entity.Book;
import com.example.bookshelf.entity.User;
import com.example.bookshelf.entity.UserBook;
import com.example.bookshelf.enums.BookStatus;
import com.example.bookshelf.exceptions.EntityConflict;
import com.example.bookshelf.exceptions.EntityNotFound;
import com.example.bookshelf.exceptions.EnumNotFound;
import com.example.bookshelf.exceptions.model.EntityError;
import com.example.bookshelf.model.UpdateBookStatusRequest;
import com.example.bookshelf.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service for managing UserBooks
 *
 * @see UserBook
 * @see UserBookRepository
 */
@Service
public class UserBookService {

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    /**
     * Lists user's books
     *
     * @param userId the user's id
     * @return a list with the user's books entities, the status plus the book itself
     * @throws EntityNotFound when user is not found
     */
    public List<UserBook> listByUserId(Long userId) {
        User user = userService.findUserById(userId);
        return userBookRepository.findByUser(user);
    }

    /**
     * Creates a user-book (links a user to a book)
     *
     * @param userId the user's id
     * @param bookId the book's id
     * @return the created user-book association entity
     * @throws EntityNotFound when either user or book are not found
     * @throws EntityConflict when an association is found for this user and book
     */
    public UserBook createByUserIdAndBookId(Long userId, Long bookId) {
        if (userBookRepository.existsByUserIdAndBookId(userId, bookId)) {
            Set<EntityError> conflictingFields = new HashSet<>();
            conflictingFields.add(new EntityError("User id", userId));
            conflictingFields.add(new EntityError("Book id", bookId));
            throw new EntityConflict("UserBook", conflictingFields);
        }

        User user = userService.findUserById(userId);
        Book book = bookService.findBookById(bookId);

        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);
        return userBookRepository.save(userBook);
    }

    /**
     * Finds a user-book by id
     *
     * @param userBookId the user-book id
     * @return the user-book entity
     * @throws EntityNotFound when user-book association is not found
     */
    public UserBook findUserBookById(Long userBookId) {
        return userBookRepository.findById(userBookId).orElseThrow(() ->
                new EntityNotFound("UserBook", userBookId));
    }

    /**
     * Updates the status of an existing user-book association
     *
     * @param userBookId    the user-book's association id
     * @param statusRequest the status change request
     * @throws EntityNotFound when user-book association is not found
     * @throws EnumNotFound when there is no match with the possible book status
     */
    public void updateUserBookStatus(Long userBookId, UpdateBookStatusRequest statusRequest) {
        UserBook userBook = findUserBookById(userBookId);

        try {
            BookStatus status = BookStatus.valueOf(statusRequest.getStatus());
            userBook.setStatus(status);
            userBookRepository.save(userBook);
        } catch (IllegalArgumentException e) {
            throw new EnumNotFound("BookStatus", statusRequest.getStatus());
        }
    }

    /**
     * Permanently deletes a user-book (unlinks a user to a book)
     *
     * @param userBookId the id of the user-book to be deleted
     * @throws EntityNotFound when user-book association is not found
     */
    public void deleteUserBook(Long userBookId) {
        UserBook userBook = findUserBookById(userBookId);
        userBookRepository.delete(userBook);
    }
}
