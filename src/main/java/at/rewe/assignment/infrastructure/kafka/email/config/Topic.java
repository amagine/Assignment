package at.rewe.assignment.infrastructure.kafka.email.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Topic {

    @Value("${kafka.topic.email}")
    private String topicName;

    @Value("${kafka.topic.email.partitions}")
    private int partitions;

    @Bean
    public NewTopic topicEmail() {
        return TopicBuilder.name(topicName)
                .partitions(partitions)
                .build();
    }
}
