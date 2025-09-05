package com.example.bookshelf.service;

import com.example.bookshelf.entity.User;
import com.example.bookshelf.exceptions.EntityNotFound;
import com.example.bookshelf.exceptions.model.EntityError;
import com.example.bookshelf.model.UserModel;
import com.example.bookshelf.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service for managing users
 *
 * @see User
 * @see UserRepository
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Lists all users
     *
     * @return a list with all users
     */
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    /**
     * Finds a user by its id
     *
     * @param id the user's id
     * @return the user entity
     * @throws EntityNotFound when user is not found
     */
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
//                new EntityNotFound("User", id));
        new EntityNotFound("User", new EntityError("id", id.toString(), null)));
    }

    /**
     * Creates a new user
     *
     * @param userModel the user's model, the id is irrelevant
     * @return the new created user entity
     */
    public User createUser(UserModel userModel) {
        return userRepository.save(modelToUser(userModel));
    }

    /**
     * Updates (overwrites) an existing user
     *
     * @param id        the id of the user to be updated
     * @param userModel the entire new user model, minus the id
     * @return the new updated user entity
     * @throws EntityNotFound when user is not found
     */
    public User updateUser(Long id, UserModel userModel) {
        User user = findUserById(id);
        user.setUsername(userModel.getUsername());
        user.setFullName(userModel.getFullName());
        return userRepository.save(user);
    }

    /**
     * Permanently deletes a user
     *
     * @param id the id of the user to be deleted
     * @throws EntityNotFound when user is not found
     */
    public void deleteUserById(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    /**
     * Converts a user model (json representation) to a user entity (entity representation)
     *
     * @param userModel the user model to be converted
     * @return the equivalent user entity
     */
    public User modelToUser(UserModel userModel) {
        User user = new User();
        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setFullName(userModel.getFullName());
        return user;
    }
}
