package com.jabito.kafkaproducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic.primary}")
    private String primaryTopic;

    @Value(value = "${kafka.topic.secondary}")
    private String secondaryTopic;

    @Value(value = "${kafka.topic.other}")
    private String otherTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic mainTopic() {
        return new NewTopic(primaryTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic subTopic() {
        return new NewTopic(secondaryTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic otherTopic() {
        return new NewTopic(otherTopic, 1, (short) 1);
    }
}