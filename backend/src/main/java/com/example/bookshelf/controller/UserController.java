package com.example.bookshelf.controller;

import com.example.bookshelf.model.UserModel;
import com.example.bookshelf.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing users, path: /users
 *
 * @see UserModel
 * @see UserService
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Lists all users
     *
     * @return a list of all users
     */
    @GetMapping
    public List<UserModel> listUsers() {
        return userService.listUsers().stream().map(UserModel::new).collect(Collectors.toList());
    }

    /**
     * Finds a user by its id
     *
     * @param id the user's id
     * @return the user
     * @throws org.springframework.web.server.ResponseStatusException with http status 404 if not found
     */
    @GetMapping("/{id}")
    public UserModel findUserById(@PathVariable Long id) {
        return new UserModel(userService.findUserById(id));
    }

    /**
     * Creates a new user
     *
     * @param userModel the user to be created, the id is irrelevant
     * @return the created user with its assigned id
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createUser(@Valid @RequestBody UserModel userModel) {
        return new UserModel(userService.createUser(userModel));
    }

    /**
     * Updates (overwrites) an existing user
     *
     * @param id        the id of the user to be updated
     * @param userModel the entire new user model, minus the id
     * @return the new updated user
     * @throws org.springframework.web.server.ResponseStatusException with http status 404 if not found
     */
    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @Valid @RequestBody UserModel userModel) {
        return new UserModel(userService.updateUser(id, userModel));
    }

    /**
     * Permanently deletes a user
     *
     * @param id the id of the user to be deleted
     * @throws org.springframework.web.server.ResponseStatusException with http status 404 if not found
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
