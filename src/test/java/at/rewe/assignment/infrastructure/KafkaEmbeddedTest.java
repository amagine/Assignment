package at.rewe.assignment.infrastructure;

import at.rewe.assignment.entity.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EmbeddedKafka(partitions = 3, topics = "emails")
@SpringBootTest
public class KafkaEmbeddedTest {

    @Autowired
    private KafkaTemplate<String, Email> kafkaTemplate;

    @Test
    public void testSendMessage() {
        Email email = new Email();
        email.setSender("user1234@gmail.com");
        kafkaTemplate.send("emails", "gmail.com", email);

        email.setSender("user1234@outlook.com");
        kafkaTemplate.send("emails", "outlook.com", email);

        email.setSender("user1234@hotmail.com");
        kafkaTemplate.send("emails", "hotmail.com", email);
    }

    @KafkaListener(topics = "topicName", groupId = "testGroup")
    public void listen(@Payload Email email, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        assertEquals("user1234@"+ (partition == 0 ? "gmail.com" : partition == 1 ? "outlook.com" : partition == 2 ? "hotmail.com" : null), email.getSender());
    }
}

