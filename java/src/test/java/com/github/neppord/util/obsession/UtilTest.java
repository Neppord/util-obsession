package com.github.neppord.util.obsession;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void hashPassword() throws NoSuchAlgorithmException {
        final String hash = Util.hashPassword("1234abcd");
        assertEquals("Ihs3/NtS0PfDm70L4hHbDhwAyl++zVeIeARjAmxrlks=", hash);
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
}