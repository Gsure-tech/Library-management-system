package com.gsuretech.librarymanagementsystem.exceptions;

public class BorrowingException extends Exception{
    public BorrowingException() {
    }

    public BorrowingException(String message) {
        super(message);
    }

    public BorrowingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BorrowingException(Throwable cause) {
        super(cause);
    }

    public BorrowingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
