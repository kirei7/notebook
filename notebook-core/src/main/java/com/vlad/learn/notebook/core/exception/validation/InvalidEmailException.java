package com.vlad.learn.notebook.core.exception.validation;

import com.vlad.learn.notebook.core.exception.DomainException;

public class InvalidEmailException extends DomainException {

    private static final String COMMON_MESSAGE = "Invalid email";

    public InvalidEmailException() {
        super(COMMON_MESSAGE);
    }

    public InvalidEmailException(String message) {
        super(composeMessage(COMMON_MESSAGE, message));
    }

}
