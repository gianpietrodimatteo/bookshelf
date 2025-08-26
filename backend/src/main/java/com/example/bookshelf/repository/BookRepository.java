package com.example.bookshelf.repository;

import com.example.bookshelf.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The book repository interface
 *
 * @see BookRepository
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
