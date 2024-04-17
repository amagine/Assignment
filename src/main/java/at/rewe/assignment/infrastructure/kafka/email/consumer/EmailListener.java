package at.rewe.assignment.infrastructure.kafka.email.consumer;

import at.rewe.assignment.application.statistic.StatsService;
import at.rewe.assignment.entity.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${kafka.topic.email}", groupId = "${kafka.topic.email.consumer.group}")
public class EmailListener {

    Logger logger = LoggerFactory.getLogger(EmailListener.class);

    private final StatsService statsService;

    @Autowired
    public EmailListener(StatsService statsService) {
        this.statsService = statsService;
    }

    @KafkaHandler
    void listen(@Payload Email email, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        logger.info("Received email: {}, from partition: {}", email.getSender(), partition);
        statsService.saveStats(email);
    }
}
