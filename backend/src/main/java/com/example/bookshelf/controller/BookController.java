package com.example.bookshelf.controller;

import com.example.bookshelf.model.BookModel;
import com.example.bookshelf.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing books, path: /books
 *
 * @see BookModel
 * @see BookService
 */
@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;


    /**
     * Lists all books
     *
     * @return a list of all books
     */
    @GetMapping()
    public List<BookModel> listBooks() {
        return bookService.listBooks().stream().map(BookModel::new).collect(Collectors.toList());
    }

    /**
     * Finds a book by its id
     *
     * @param id the book's id
     * @return the book
     * @throws org.springframework.web.server.ResponseStatusException with http status 404 if not found
     */
    @GetMapping("/{id}")
    public BookModel findBookById(@PathVariable Long id) {
        return new BookModel(bookService.findBookById(id));
    }

    /**
     * Creates a new book
     *
     * @param bookModel the book to be created, the id is irrelevant
     * @return the created book with its assigned id
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookModel createBook(@Valid @RequestBody BookModel bookModel) {
        return new BookModel(bookService.createBook(bookModel));
    }

    /**
     * Updates (overwrites) an existing book
     *
     * @param id        the id of the book to be updated
     * @param bookModel the entire new book model, minus the id
     * @return the new updated book
     * @throws org.springframework.web.server.ResponseStatusException with http status 404 if not found
     */
    @PutMapping("/{id}")
    public BookModel updateBook(@PathVariable Long id, @Valid @RequestBody BookModel bookModel) {
        return new BookModel(bookService.updateBook(id, bookModel));
    }

    /**
     * Permanently deletes a book
     *
     * @param id the id of the book to be deleted
     * @throws org.springframework.web.server.ResponseStatusException with http status 404 if not found
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
