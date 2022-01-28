package br.com.er.votacaoapi.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value(value = "${kafka.bootstrapServerAddress}")
    private String bootstrapServerAddress;

    @Value(value = "${vote-results-topic}")
    private String topicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic voteResultsTopic() {
        return new NewTopic(topicName, 3, (short) 1);
    }
}
