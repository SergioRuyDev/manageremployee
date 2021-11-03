package com.sergioruy.manageremployee.exception;


public class UserNotFoundException extends RuntimeException {

    private static final long serialUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this(String.format("Not exist User with code %d", id));
    }
}
