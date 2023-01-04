package com.sergioruy.manageremployee.exception;


public class ResourceNotFoundException extends RuntimeException {

    private static final long serialUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Long id) {
        this(String.format("Not exist User with code %d", id));
    }
}
