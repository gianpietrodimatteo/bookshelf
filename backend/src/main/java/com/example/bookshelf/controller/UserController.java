package com.example.bookshelf.controller;

import com.example.bookshelf.model.UserModel;
import com.example.bookshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> listUsers() {
        return userService.listUsers().stream().map(UserModel::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserModel findUserById(@PathVariable Long id) {
        return new UserModel(userService.findUserById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createUser(@RequestBody UserModel userModel) {
        return new UserModel(userService.createUser(userModel));
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel userModel) {
        return new UserModel(userService.updateUser(id, userModel));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
