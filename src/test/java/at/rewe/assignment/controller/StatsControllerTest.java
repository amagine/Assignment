package at.rewe.assignment.controller;

import at.rewe.assignment.application.email.EmailServiceImpl;
import at.rewe.assignment.application.statistic.StatsServiceImpl;
import at.rewe.assignment.entity.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(StatsController.class)
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsServiceImpl statsService;

    @MockBean
    private EmailServiceImpl emailService;

    @Test
    public void testGetEmailStats() throws Exception {
        when(emailService.generateRandomEmail()).thenReturn("user123@gmail.com");
        String randomEmail = emailService.generateRandomEmail();

        Email email = generateEmail(randomEmail);
        List<Email> emails = Collections.singletonList(email);
        when(statsService.retrieveStats()).thenReturn(emails);

        mockMvc.perform(MockMvcRequestBuilders.get("/email/statistic")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(emails.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sender").value(email.getSender()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subject").value(email.getSubject()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(email.getContent()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipients").value(email.getRecipients()))
                .andReturn();
    }

    private Email generateEmail(String randomEmail) {
        Email email = new Email();
        email.setSubject("Regarding Assignment");
        email.setContent("This is a sample content");
        email.setSender(randomEmail);

        List<String> recipients = new ArrayList<>();
        recipients.add("recipient@domain.com");
        email.setRecipients(recipients);

        return email;
    }
}