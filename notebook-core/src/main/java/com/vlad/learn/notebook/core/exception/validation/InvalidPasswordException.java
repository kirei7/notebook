package com.vlad.learn.notebook.core.exception.validation;

import com.vlad.learn.notebook.core.exception.DomainException;

public class InvalidPasswordException extends DomainException {

    private static final String COMMON_MESSAGE = "Invalid password";

    public InvalidPasswordException() {
        super(COMMON_MESSAGE);
    }

    public InvalidPasswordException(String message) {
        super(composeMessage(COMMON_MESSAGE, message));
    }

}
