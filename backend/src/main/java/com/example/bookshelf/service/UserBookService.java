package com.example.bookshelf.service;

import com.example.bookshelf.entity.Book;
import com.example.bookshelf.entity.User;
import com.example.bookshelf.entity.UserBook;
import com.example.bookshelf.enums.BookStatus;
import com.example.bookshelf.model.UpdateBookStatusRequest;
import com.example.bookshelf.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserBookService {

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    public List<UserBook> listByUserId(Long userId) {
        User user = userService.findUserById(userId);
        return userBookRepository.findByUser(user);
    }

    public UserBook createByUserIdAndBookId(Long userId, Long bookId) {
        if (userBookRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "There is already an association in place for this user for this book");
        }

        User user = userService.findUserById(userId);
        Book book = bookService.findBookById(bookId);

        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);
        return userBookRepository.save(userBook);
    }

    public UserBook findUserBookById(Long userBookId) {
        return userBookRepository.findById(userBookId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User's Book not found!"));
    }

    public void updateUserBookStatus(Long userBookId, UpdateBookStatusRequest statusRequest) {
        UserBook userBook = findUserBookById(userBookId);

        try {
            BookStatus status = BookStatus.valueOf(statusRequest.getStatus());
            userBook.setStatus(status);
            userBookRepository.save(userBook);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User's Book status!");
        }
    }

    public void deleteUserBook(Long userBookId) {
        UserBook userBook = findUserBookById(userBookId);
        userBookRepository.delete(userBook);
    }
}
