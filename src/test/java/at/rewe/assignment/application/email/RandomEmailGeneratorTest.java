package at.rewe.assignment.application.email;

import at.rewe.assignment.utility.EmailValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RandomEmailGeneratorTest {

    @Autowired
    private EmailGenerator emailGenerator;

    @Test
    void testGenerateValidEmail() {
        String email = emailGenerator.generate();
        assertNotNull(email);
        assertTrue(EmailValidator.validate(email));
    }

    @Test
    void testGeneratedEmailsAreDifferent() {
        String email1 = emailGenerator.generate();
        String email2 = emailGenerator.generate();
        String email3 = emailGenerator.generate();

        assertTrue(!email1.equals(email2) && !email1.equals(email3) && !email2.equals(email3));
    }
}