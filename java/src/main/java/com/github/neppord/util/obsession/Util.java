package com.github.neppord.util.obsession;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

class Util {

    static String hashPassword(String password) throws NoSuchAlgorithmException {
        final byte[] sha256 = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
        return Base64.getEncoder().encodeToString(sha256);
    }

    static void validatePassword(String password) throws PasswordValidationException {
        validatePasswordLength(password);
        validatePasswordCharacters(password);
    }

    private static void validatePasswordCharacters(String password) throws PasswordValidationException {
        if(!password.matches(".*\\d.*")) {
            throw new PasswordValidationException(
                "Password needs at least one digit"
            );
        }
        if(!password.matches(".*[A-Za-z].*")) {
            throw new PasswordValidationException(
                "Password needs at least one alpha"
            );
        }
    }

    private static void validatePasswordLength(String password) throws PasswordValidationException {
        if (password.length() < 8) {
            throw new PasswordValidationException(
                "Password to short (needs to be atleast 8 characters long)"
            );
        }
    }
}
