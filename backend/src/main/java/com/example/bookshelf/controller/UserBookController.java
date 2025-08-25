package com.example.bookshelf.controller;

import com.example.bookshelf.model.UpdateBookStatusRequest;
import com.example.bookshelf.model.UserBookModel;
import com.example.bookshelf.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user-books")
public class UserBookController {

    @Autowired
    private UserBookService userBookService;


    @GetMapping
    public List<UserBookModel> listByUserId(@RequestParam Long userId) {
        return userBookService.listByUserId(userId).stream().map(UserBookModel::new).collect(Collectors.toList());
    }

    @PostMapping("/user/{userId}/book/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserBookModel createByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        return new UserBookModel(userBookService.createByUserIdAndBookId(userId, bookId));
    }

    @PatchMapping("/{userBookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserBookStatus(@PathVariable Long userBookId, @RequestBody UpdateBookStatusRequest statusRequest) {
        userBookService.updateUserBookStatus(userBookId, statusRequest);
    }

    @DeleteMapping("/{userBookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserBookById(@PathVariable Long userBookId) {
        userBookService.deleteUserBook(userBookId);
    }
}
