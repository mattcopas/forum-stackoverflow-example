package com.qa.consulting.forum.exception;

/**
 * Created by Matt on 24/07/2017.
 */
public class UserExistsException extends RuntimeException {

    public UserExistsException(String message) {
        super(message);
    }
}
