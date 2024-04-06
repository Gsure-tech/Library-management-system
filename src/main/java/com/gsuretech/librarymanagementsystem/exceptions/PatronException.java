package com.gsuretech.librarymanagementsystem.exceptions;

public class PatronException extends Exception{
    public PatronException() {
    }

    public PatronException(String message) {
        super(message);
    }

    public PatronException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatronException(Throwable cause) {
        super(cause);
    }

    public PatronException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
