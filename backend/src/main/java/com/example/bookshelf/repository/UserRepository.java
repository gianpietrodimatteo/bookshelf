package com.example.bookshelf.repository;


import com.example.bookshelf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
