package at.rewe.assignment.infrastructure.kafka.email.config;

import at.rewe.assignment.entity.Email;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Producer.class)
class ProducerTest {

    @Autowired
    private DefaultKafkaProducerFactory<String, Email> producerFactory;

    @Test
    public void testProducerConfigs() {
        var configs = producerFactory.getConfigurationProperties();
        assertEquals(configs.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG), "localhost:9092");
        assertEquals(configs.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG), StringSerializer.class);
        assertEquals(configs.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG), JsonSerializer.class);
        assertEquals(configs.get(ProducerConfig.PARTITIONER_CLASS_CONFIG), DomainPartitioner.class);
    }
}