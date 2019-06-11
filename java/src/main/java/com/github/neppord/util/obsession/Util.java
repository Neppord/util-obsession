package com.github.neppord.util.obsession;

import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    static String getPassword(String json) {
        return getValueFromJson(json, "password");
    }

    static String getUsername(String json) {
        return getValueFromJson(json, "username");
    }

    private static String getValueFromJson(String json, String key) {
        final Scanner scanner = new Scanner(new StringReader(json));
        final Stream<MatchResult> results = scanner.findAll("\"([^\"]*)\"\\s*:\\s*\"([^\"]*)\"");
        for (MatchResult m: results.collect(Collectors.toList())) {
            if (m.group(1).equals(key)) {
                return m.group(2);
            }
        }
        return "";
    }
}
