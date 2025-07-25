package com.saga.pattern.SagaOrchestration.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processPayment(String orderId) {
        // Simulate payment success or failure
        String paymentStatus = "Payment Success for order: " + orderId;
        kafkaTemplate.send("payment-events-topic", paymentStatus);
    }
}
