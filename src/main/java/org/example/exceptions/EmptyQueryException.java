package org.example.exceptions;

public class EmptyQueryException extends RuntimeException {
    public EmptyQueryException(String message) {
        super(message);
    }
}
