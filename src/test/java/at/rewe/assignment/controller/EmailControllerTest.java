package at.rewe.assignment.controller;

import at.rewe.assignment.application.email.EmailServiceImpl;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailServiceImpl emailService;

    @Test
    void testGenerateEmailWithValidEmailAddress() throws Exception {

        when(emailService.generateRandomEmail()).thenReturn("user123@gmail.com");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/email/generate"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertTrue(EmailValidator.getInstance().isValid(responseBody));
    }

    @Test
    void testGenerateEmailWithInValidEmailAddress() throws Exception {

        when(emailService.generateRandomEmail()).thenReturn("user123");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/email/generate"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertFalse(EmailValidator.getInstance().isValid(responseBody));
    }
}