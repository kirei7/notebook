package com.vlad.learn.notebook.core.exception;

public class UserAccountNotFoundException extends DomainException{

    private static final String COMMON_MESSAGE = "User account not found";

    public UserAccountNotFoundException() {
        super(COMMON_MESSAGE);
    }

    public UserAccountNotFoundException(String message) {
        super(composeMessage(COMMON_MESSAGE, message));
    }
}
