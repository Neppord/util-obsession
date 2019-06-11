package com.github.neppord.util.obsession;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void hashPassword() throws NoSuchAlgorithmException {
        assertEquals("Ihs3/NtS0PfDm70L4hHbDhwAyl++zVeIeARjAmxrlks=", Util.hashPassword("1234abcd"));
        assertEquals("6c7nGrky/ehjM40Ivk3p3+OeoEm9r7NCzmWexUULaa4=", Util.hashPassword("abcd1234"));
    }

    @Test
    public void validateValidPassword() throws PasswordValidationException {
        Util.validatePassword("1234abcd");
    }

    @Test(expected = PasswordValidationException.class)
    public void validateToShortPassword() throws PasswordValidationException {
        Util.validatePassword("1234abc");
    }

    @Test(expected = PasswordValidationException.class)
    public void validatePasswordMissingNumber() throws PasswordValidationException {
        Util.validatePassword("abcdabcd");
    }

    @Test(expected = PasswordValidationException.class)
    public void validatePasswordMissingAlpha() throws PasswordValidationException {
        Util.validatePassword("12341234");
    }

    @Test
    public void getPasswordFromUserBlob() {
        assertEquals(
            "abcd1234",
            Util.getPassword("{\"password\": \"abcd1234\", \"username\": \"smith\"}")
        );
        assertEquals(
            "abcd1234",
            Util.getPassword("{\"username\": \"smith\", \"password\": \"abcd1234\"}")
        );
        assertEquals(
            "abcd5678",
            Util.getPassword("{\"password\": \"abcd5678\", \"username\": \"smith\"}")
        );
    }

    @Test
    public void getUsernameFromUserBlob() {
        assertEquals(
            "smith",
            Util.getUsername("{\"password\": \"abcd1234\", \"username\": \"smith\"}")
        );
        assertEquals(
            "smith",
            Util.getUsername("{\"username\": \"smith\", \"password\": \"abcd1234\"}")
        );
        assertEquals(
            "john",
            Util.getUsername("{\"password\": \"abcd5678\", \"username\": \"john\"}")
        );
    }

    @Test
    public void createUserBlobFromUsernameAndPassword() {
        String password = "abcd1234";
        String username = "smith";
        assertEquals(
            "{\"password\": \"abcd1234\", \"username\": \"smith\"}",
            Util.createUserBlob(password, username)
        );
    }
}