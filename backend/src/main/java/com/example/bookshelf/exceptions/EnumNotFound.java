package com.example.bookshelf.exceptions;

public class EnumNotFound extends RuntimeException {

    public EnumNotFound(String enumClassName, String name) {
        super(String.format("%s of name %s not found.", enumClassName, name));
    }
}
