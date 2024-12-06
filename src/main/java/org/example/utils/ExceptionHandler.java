package org.example.utils;

public class ExceptionHandler {
    public static void handleException(Exception e, String userMessage) {
        System.err.println(userMessage);
        e.printStackTrace(); // Fejlesztői környezetben rögzíthetjük a naplóban
    }
}
