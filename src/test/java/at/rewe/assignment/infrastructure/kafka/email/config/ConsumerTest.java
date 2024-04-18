package at.rewe.assignment.infrastructure.kafka.email.config;

import at.rewe.assignment.entity.Email;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Consumer.class)
class ConsumerTest {

    @Autowired
    private DefaultKafkaConsumerFactory<String, Email> consumerFactory;

    @Test
    public void testConsumerConfigs() {
        var configs = consumerFactory.getConfigurationProperties();
        assertEquals(configs.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG), "localhost:9092");
        assertEquals(configs.get(ConsumerConfig.GROUP_ID_CONFIG), "email-consumer-group");
        assertEquals(configs.get(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG), "earliest");
        assertEquals(configs.get(JsonDeserializer.TRUSTED_PACKAGES), Email.class.getPackageName());
    }
}