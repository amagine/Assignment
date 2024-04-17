package at.rewe.assignment.infrastructure.kafka.email.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class DomainPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String domain = (String) key;
        return switch (domain) {
            case "gmail.com" -> 0;
            case "outlook.com" -> 1;
            default -> 2;
        };
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
