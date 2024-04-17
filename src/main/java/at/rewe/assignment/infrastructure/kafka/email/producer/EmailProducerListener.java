package at.rewe.assignment.infrastructure.kafka.email.producer;

import at.rewe.assignment.entity.Email;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class EmailProducerListener implements ProducerListener<String, Email> {

    private final Logger logger = LoggerFactory.getLogger(EmailProducerListener.class);

    @Override
    public void onSuccess(ProducerRecord<String, Email> record, RecordMetadata metadata) {
        ProducerListener.super.onSuccess(record, metadata);
        logger.info("Message sent successfully - Topic: {}, Partition: {}, Offset: {}",
                metadata.topic(), metadata.partition(), metadata.offset());
    }

    @Override
    public void onError(ProducerRecord<String, Email> record, RecordMetadata recordMetadata, Exception exception) {
        ProducerListener.super.onError(record, recordMetadata, exception);
        logger.error("Error sending message - Topic: {}, Message: {}, Exception: {}",
                record.topic(), record.value(), exception.getMessage());
    }
}
