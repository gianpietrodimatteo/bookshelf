package com.example.bookshelf.controller;

import com.example.bookshelf.model.BookModel;
import com.example.bookshelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping()
    public List<BookModel> listBooks() {
        return bookService.listBooks().stream().map(BookModel::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookModel findBookById(@PathVariable Long id) {
        return new BookModel(bookService.findBookById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookModel createBook(@RequestBody BookModel bookModel) {
        return new BookModel(bookService.createBook(bookModel));
    }

    @PutMapping("/{id}")
    public BookModel updateBook(@PathVariable Long id, @RequestBody BookModel bookModel) {
        return new BookModel(bookService.updateBook(id, bookModel));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
