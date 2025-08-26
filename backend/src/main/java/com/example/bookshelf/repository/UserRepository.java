package com.example.bookshelf.repository;


import com.example.bookshelf.entity.User;
import com.example.bookshelf.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The user repository interface
 *
 * @see UserService
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
