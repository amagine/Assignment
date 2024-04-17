package at.rewe.assignment.infrastructure.kafka.email.producer;

import at.rewe.assignment.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {

    @Value("${kafka.topic.email}")
    private String topic;

    private final KafkaTemplate<String, Email> kafkaTemplate;

    @Autowired
    public EmailProducer(KafkaTemplate<String, Email> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Email email) {
        this.kafkaTemplate.send(topic, email.getDomain(), email);
    }
}
