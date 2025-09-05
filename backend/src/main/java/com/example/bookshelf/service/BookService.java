package com.example.bookshelf.service;

import com.example.bookshelf.entity.Book;
import com.example.bookshelf.exceptions.EntityNotFound;
import com.example.bookshelf.exceptions.model.EntityError;
import com.example.bookshelf.model.BookModel;
import com.example.bookshelf.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service for managing books
 *
 * @see Book
 * @see BookRepository
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    /**
     * Lists all books
     *
     * @return a list with all books
     */
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    /**
     * Finds a book by its id
     *
     * @param id the book's id
     * @return the book entity
     * @throws EntityNotFound when book is not found
     */
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
//                new EntityNotFound("Book", id));
        new EntityNotFound("Book", new EntityError("id", id.toString(), null)));
    }

    /**
     * Creates a new book
     *
     * @param bookModel the book's model, the id is irrelevant
     * @return the new created book entity
     */
    public Book createBook(BookModel bookModel) {
        return bookRepository.save(modelToBook(bookModel));
    }

    /**
     * Updates (overwrites) an existing book
     *
     * @param id        the id of the book to be updated
     * @param bookModel the entire new book model, minus the id
     * @return the new updated book entity
     * @throws EntityNotFound when book is not found
     */
    public Book updateBook(Long id, BookModel bookModel) {
        Book book = findBookById(id);
        book.setName(bookModel.getName());
        book.setAuthor(bookModel.getAuthor());
        book.setYear(bookModel.getYear());
        book.setGenre(bookModel.getGenre());

        return bookRepository.save(book);
    }

    /**
     * Permanently deletes a book
     *
     * @param id the id of the book to be deleted
     * @throws EntityNotFound when book is not found
     */
    public void deleteBookById(Long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }

    /**
     * Converts a book model (json representation) to a book entity (entity representation)
     *
     * @param bookModel the book model to be converted
     * @return the equivalent book entity
     */
    public Book modelToBook(BookModel bookModel) {
        Book book = new Book();
        book.setName(bookModel.getName());
        book.setAuthor(bookModel.getAuthor());
        book.setYear(bookModel.getYear());
        book.setGenre(bookModel.getGenre());

        return book;
    }
}
