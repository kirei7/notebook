package com.vlad.learn.notebook.core.exception;

public abstract class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    protected static String composeMessage(String commonMessage, String infoMessage) {
        return commonMessage + ": [" + infoMessage + "]";
    }

}
