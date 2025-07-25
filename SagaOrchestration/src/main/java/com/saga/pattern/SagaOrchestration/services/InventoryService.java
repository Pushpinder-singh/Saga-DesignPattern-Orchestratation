package com.saga.pattern.SagaOrchestration.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
	private final KafkaTemplate<String, String> kafkaTemplate;

    public InventoryService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void reserveInventory(String orderId) {
        String inventoryStatus = "Inventory Reserved for order: " + orderId;
        kafkaTemplate.send("inventory-events-topic", inventoryStatus);
    }
}
