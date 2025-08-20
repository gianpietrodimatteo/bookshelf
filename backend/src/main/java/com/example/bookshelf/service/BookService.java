package com.example.bookshelf.service;

import com.example.bookshelf.entity.Book;
import com.example.bookshelf.entity.Book;
import com.example.bookshelf.model.BookModel;
import com.example.bookshelf.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;


    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!"));
    }

    public Book createBook(BookModel bookModel) {
        return bookRepository.save(modelToBook(bookModel));
    }

    public Book updateBook(Long id, BookModel bookModel) {
        Book book = findBookById(id);
        book.setName(bookModel.getName());
        book.setAuthor(bookModel.getAuthor());
        book.setYear(bookModel.getYear());
        book.setGenre(bookModel.getGenre());

        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }

    public Book modelToBook(BookModel bookModel) {
        Book book = new Book();
        book.setName(bookModel.getName());
        book.setAuthor(bookModel.getAuthor());
        book.setYear(bookModel.getYear());
        book.setGenre(bookModel.getGenre());

        return book;
    } 
}
