package com.example.bookshelf.controller;

import com.example.bookshelf.model.UpdateBookStatusRequest;
import com.example.bookshelf.model.UserBookModel;
import com.example.bookshelf.service.UserBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing user's book list, path: /user-books
 *
 * @see UserBookModel
 * @see UserBookService
 */
@RestController
@RequestMapping("user-books")
public class UserBookController {

    @Autowired
    private UserBookService userBookService;


    /**
     * Lists user's books
     *
     * @param userId the user's id
     * @return a list with the user's books, their status plus the book itself
     * @throws ResponseStatusException with http status 404 if not found
     */
    @GetMapping
    public List<UserBookModel> listByUserId(@RequestParam Long userId) {
        return userBookService.listByUserId(userId).stream().map(UserBookModel::new).collect(Collectors.toList());
    }

    /**
     * Creates a user-book (links a user to a book)
     *
     * @param userId the user's id
     * @param bookId the book's id
     * @return the created user-book association with its assigned id
     * @throws ResponseStatusException with http status 404 if not found
     * @throws ResponseStatusException with http status 409 if it's a duplicate (same user same book)
     */
    @PostMapping("/user/{userId}/book/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserBookModel createByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        return new UserBookModel(userBookService.createByUserIdAndBookId(userId, bookId));
    }

    /**
     * Updates the status of an existing user-book association
     *
     * @param userBookId    the user-book's association id
     * @param statusRequest the new status on a field in a json body in string format
     * @throws ResponseStatusException with http status 404 if not found
     */
    @PatchMapping("/{userBookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserBookStatus(@PathVariable Long userBookId, @Valid @RequestBody UpdateBookStatusRequest statusRequest) {
        userBookService.updateUserBookStatus(userBookId, statusRequest);
    }

    /**
     * Permanently deletes a user-book (unlinks a user to a book)
     *
     * @param userBookId the id of the user-book to be deleted
     * @throws ResponseStatusException with http status 404 if not found
     */
    @DeleteMapping("/{userBookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserBookById(@PathVariable Long userBookId) {
        userBookService.deleteUserBook(userBookId);
    }
}
