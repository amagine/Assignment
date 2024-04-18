package at.rewe.assignment.application.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @MockBean
    EmailGenerator emailGeneratorMock;

    @Test
    public void testGenerateRandomEmail() {
        String mail = "user1234@gmail.com";
        when(emailGeneratorMock.generate()).thenReturn(mail);
        String randomEmail = emailService.generateRandomEmail();
        assertNotNull(randomEmail);
        assertEquals(mail, randomEmail);
    }

    @Test
    public void testGenerateRandomEmailWithException() {
        when(emailGeneratorMock.generate()).thenThrow(new IllegalStateException("error in generating an email"));
        String randomEmail = emailService.generateRandomEmail();
        assertNull(randomEmail);
    }
}