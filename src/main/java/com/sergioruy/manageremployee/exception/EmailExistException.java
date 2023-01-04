package com.sergioruy.manageremployee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailExistException extends RuntimeException{

    private static final long serialUID = 1L;

    public EmailExistException(String message) {
        super(message);
    }

}
