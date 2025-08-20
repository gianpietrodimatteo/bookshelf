package com.example.bookshelf.controller;

import com.example.bookshelf.model.BookModel;
import com.example.bookshelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public List<BookModel> listBooks() {
        return bookService.listBooks().stream().map(BookModel::new).collect(Collectors.toList());
    }

    @GetMapping
    public BookModel findBookById(@RequestParam Long id) {
        return new BookModel(bookService.findBookById(id));
    }

    @PostMapping
    public BookModel createBook(@RequestBody BookModel bookModel) {
        return new BookModel(bookService.createBook(bookModel));
    }

    @PutMapping
    public BookModel updateBook(@RequestParam Long id, @RequestBody BookModel bookModel) {
        return new BookModel(bookService.updateBook(id, bookModel));
    }

    @DeleteMapping
    public void deleteBookById(@RequestParam Long id) {
        bookService.deleteBookById(id);
    }
}
