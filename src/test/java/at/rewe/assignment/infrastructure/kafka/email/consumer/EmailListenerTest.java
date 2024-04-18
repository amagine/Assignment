package at.rewe.assignment.infrastructure.kafka.email.consumer;

import at.rewe.assignment.application.statistic.StatsService;
import at.rewe.assignment.entity.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.kafka.consumer.bootstrap-servers}}")
@EmbeddedKafka(partitions = 3, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmailListenerTest {

    @Value("${kafka.topic.email}")
    private String topic;

    private Producer<String, Object> producer;

    @Autowired
    private EmbeddedKafkaKraftBroker embeddedKafkaKraftBroker;

    @BeforeAll
    void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaKraftBroker));
        DefaultKafkaProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new JsonSerializer<>());
        producer = producerFactory.createProducer();
    }

    @AfterAll
    void shutdown() {
        producer.close();
    }

    @Test
    void testConsumeMessage() {
        Email email = new Email();
        email.setSender("user1234@gmail.com");
        email.setSubject("Test subject");
        email.setContent("Test content");
        email.setRecipients(List.of("recipient@gmail.com"));
        producer.send(new ProducerRecord<>(topic, email.getDomain(), email));
        producer.flush();
    }
}