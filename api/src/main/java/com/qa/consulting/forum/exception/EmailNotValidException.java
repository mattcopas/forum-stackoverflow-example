package com.qa.consulting.forum.exception;

/**
 * Created by Matt on 24/07/2017.
 */
public class EmailNotValidException extends RuntimeException {

    public EmailNotValidException(String message) {
        super(message);
    }
}
