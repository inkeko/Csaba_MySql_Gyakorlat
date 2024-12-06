package org.example.exceptions;

public class PdfProcessingException extends RuntimeException {
    public PdfProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
