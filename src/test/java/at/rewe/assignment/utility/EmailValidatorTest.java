package at.rewe.assignment.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    void testValidEmail() {
        assertTrue(EmailValidator.validate("user123@gmail.com"));
    }

    @Test
    void testInValidEmail() {
        assertFalse(EmailValidator.validate("user123"));
    }

    @Test
    void testNullEmail() {
        assertFalse(EmailValidator.validate(null));
    }

    @Test
    void testEmptyEmail() {
        assertFalse(EmailValidator.validate(""));
    }
}