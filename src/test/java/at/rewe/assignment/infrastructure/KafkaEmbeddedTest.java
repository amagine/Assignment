package at.rewe.assignment.infrastructure;

import at.rewe.assignment.entity.Email;
import at.rewe.assignment.infrastructure.kafka.email.producer.EmailProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EmbeddedKafka(partitions = 3, topics = "emails")
@SpringBootTest
public class KafkaEmbeddedTest {

    @Autowired
    private EmailProducer emailProducer;

    @Test
    public void testSendMessage() {
        Email email = new Email();
        email.setSender("user1234@gmail.com");
        emailProducer.sendMessage(email);

        email.setSender("user1234@outlook.com");
        emailProducer.sendMessage(email);

        email.setSender("user1234@hotmail.com");
        emailProducer.sendMessage(email);
    }

    @KafkaListener(topics = "topicName", groupId = "email-consumer-group")
    public void listen(@Payload Email email, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        assertEquals("user1234@"+ (partition == 0 ? "gmail.com" : partition == 1 ? "outlook.com" : partition == 2 ? "hotmail.com" : null), email.getSender());
    }
}

