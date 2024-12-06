package org.example.utils;

import org.example.exceptions.InvalidPasswordException;
import org.springframework.stereotype.Component;

@Component
public class InputValidator {

    public void validatePassword(String password) throws InvalidPasswordException {
        if (!password.matches("[a-zA-Z0-9]+")) {
            throw new InvalidPasswordException("A jelszó csak számokat, kisbetűket és nagybetűket tartalmazhat!");
        }
        if (password.length() < 8) {
            throw new InvalidPasswordException("A jelszónak legalább 8 karakter hosszúnak kell lennie!");
        }
    }
}

