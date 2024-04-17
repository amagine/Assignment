package at.rewe.assignment.application.email;

import at.rewe.assignment.entity.Email;
import at.rewe.assignment.infrastructure.kafka.email.producer.EmailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailGenerator emailGenerator;
    private final EmailProducer emailProducer;

    @Autowired
    public EmailServiceImpl(EmailGenerator emailGenerator, EmailProducer emailProducer) {
        this.emailGenerator = emailGenerator;
        this.emailProducer = emailProducer;
    }

    @Override
    public String generateRandomEmail() {
        String emailAddress = emailGenerator.generate();

        Email email = new Email();
        email.setSubject("Regarding Assignment");
        email.setContent("This is a sample content");
        email.setSender(emailAddress);

        List<String> recipients = new ArrayList<>();
        recipients.add("recipient@domain.com");

        email.setRecipients(recipients);

        emailProducer.sendMessage(email);
        return emailAddress;
    }
}
