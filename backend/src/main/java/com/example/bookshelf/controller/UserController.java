package com.example.bookshelf.controller;

import com.example.bookshelf.model.UserModel;
import com.example.bookshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<UserModel> listUsers() {
        return userService.listUsers().stream().map(UserModel::new).collect(Collectors.toList());
    }

    @GetMapping
    public UserModel findUserById(@RequestParam Long id) {
        return new UserModel(userService.findUserById(id));
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel userModel) {
        return new UserModel(userService.createUser(userModel));
    }

    @PutMapping
    public UserModel updateUser(@RequestParam Long id, @RequestBody UserModel userModel) {
        return new UserModel(userService.updateUser(id, userModel));
    }

    @DeleteMapping
    public void deleteUserById(@RequestParam Long id) {
        userService.deleteUserById(id);
    }

}
