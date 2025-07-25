package com.saga.pattern.SagaOrchestration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class SagaOrchestrator {
	/*
	 * The orchestrator listens to the events and manages the saga flow. */
	
	@Autowired
	KafkaTemplate<String, String> KafkaTemplate;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	InventoryService inventoryService;
	
	@KafkaListener(topics = "order-topic", groupId = "saga-group")
    public void handleOrderEvent(String event) {
        System.out.println("Received Order Event: " + event);
		// Send Payment request after order creation
        paymentService.processPayment(event);
        // Publish to payment-events topic
    }

    @KafkaListener(topics = "payment-events-topic", groupId = "saga-group")
    public void handlePaymentEvent(String paymentEvent) {
        System.out.println("Received Payment Event: " + paymentEvent);
        // Trigger Inventory service after successful payment
        if ("SUCCESS".equals(paymentEvent)) {
            KafkaTemplate.send("inventory-topic", "Reserve Inventory");
//            inventoryService.reserveInventory(event);
        } else {
        	 // else rollback if payment fails
            KafkaTemplate.send("order-topic", "Cancel Order");
        }
    }

    @KafkaListener(topics = "inventory-events-topic", groupId = "saga-group")
    public void handleInventoryEvent(String inventoryStatusEvent) {
        System.out.println("Received Inventory Event: " + inventoryStatusEvent);
        if ("RESERVED".equals(inventoryStatusEvent)) {
        	// Finalize order if inventory is reserved
            KafkaTemplate.send("order-topic", "Complete Order");
        } else {
            KafkaTemplate.send("order-topic", "Cancel Order");
        }

    }

}
