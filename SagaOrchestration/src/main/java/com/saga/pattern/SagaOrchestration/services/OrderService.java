package com.saga.pattern.SagaOrchestration.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.saga.pattern.SagaOrchestration.dto.OrderDTO;

@Service
public class OrderService {
	private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeOrder(OrderDTO orderRequest) {
        String orderEvent = String.format("Order Placed: %s", orderRequest.getOrderId());
        kafkaTemplate.send("order-topic", orderEvent);
        System.out.println("Order event published: " + orderEvent);
    }
}
