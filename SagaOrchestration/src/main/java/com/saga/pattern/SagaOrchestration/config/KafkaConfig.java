package com.saga.pattern.SagaOrchestration.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	@Bean
    public NewTopic orderTopic() {
        return new NewTopic("order-events-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic("payment-events-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic inventoryTopic(){
        return TopicBuilder.name("inventory-events-topic")
                		   .build();
    }
}
