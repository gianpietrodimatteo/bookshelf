package com.example.bookshelf.repository;

import com.example.bookshelf.entity.Book;
import com.example.bookshelf.entity.User;
import com.example.bookshelf.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    List<UserBook> findByUser(User user);

    boolean existsByUserIdAndBookId(Long userId, Long bookId);

    Optional<UserBook> findByUserAndBook(User user, Book book);
}
