package com.example.bookshelf.model;

import com.example.bookshelf.entity.User;

import java.util.Objects;

/**
 * The User model, as it is in the JSON
 */
public class UserModel {

    private Long id;

    private String username;

    private String fullName;


    public UserModel(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
    }

    public UserModel(Long id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
    }

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(username, userModel.username) && Objects.equals(fullName, userModel.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullName);
    }


}
