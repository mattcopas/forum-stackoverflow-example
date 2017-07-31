package com.qa.consulting.forum.exception;

/**
 * Created by Matt on 31/07/2017.
 */
public class EntityDoesNotBelongToUserException extends RuntimeException {

    public EntityDoesNotBelongToUserException(String message) {
        super(message);
    }
}
