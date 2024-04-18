package at.rewe.assignment.controller;

import at.rewe.assignment.application.email.EmailServiceImpl;
import at.rewe.assignment.utility.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> generateEmail() {
        String randomEmail = emailServiceImpl.generateRandomEmail();
        boolean isValid = EmailValidator.validate(randomEmail);
        if (!isValid) {
            return ResponseEntity.badRequest().body(randomEmail);
        }
        return ResponseEntity.ok(randomEmail);
    }
}
