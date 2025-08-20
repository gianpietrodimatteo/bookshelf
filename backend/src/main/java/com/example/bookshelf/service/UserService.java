package com.example.bookshelf.service;

import com.example.bookshelf.entity.User;
import com.example.bookshelf.model.UserModel;
import com.example.bookshelf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public User createUser(UserModel userModel) {
        return userRepository.save(modelToUser(userModel));
    }

    public User updateUser(Long id, UserModel userModel) {
        User user = findUserById(id);
        user.setUsername(userModel.getUsername());
        user.setFullName(userModel.getFullName());
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    public User modelToUser(UserModel userModel) {
        User user = new User();
        user.setUsername(userModel.getUsername());
        user.setFullName(userModel.getFullName());
        return user;
    }
}
