package at.rewe.assignment.application.statistic;

import at.rewe.assignment.entity.Email;
import at.rewe.assignment.infrastructure.persistence.EmailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StatsServiceImplTest {

    @Autowired
    private StatsService statsService;

    @MockBean
    private EmailRepository emailRepository;

    @Test
    public void testRetrieveStats() {
        when(emailRepository.findAll()).thenReturn(List.of());
        Iterable<Email> emails = statsService.retrieveStats();
        assertNotNull(emails);
        assertFalse(emails.iterator().hasNext());
    }

    @Test
    public void testSaveStats() {
        Email email = new Email();
        email.setSender("user1234@gmail.com");
        when(emailRepository.save(email)).thenReturn(email);

        statsService.saveStats(email);
    }
}