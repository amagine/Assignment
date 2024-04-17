package at.rewe.assignment.controller;

import at.rewe.assignment.application.email.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailServiceImpl emailServiceImpl;

    @Autowired
    public EmailController(EmailServiceImpl emailServiceImpl) {
        this.emailServiceImpl = emailServiceImpl;
    }

    @PostMapping("/generate")
    public String generateEmail() {
        return emailServiceImpl.generateRandomEmail();
    }
}
