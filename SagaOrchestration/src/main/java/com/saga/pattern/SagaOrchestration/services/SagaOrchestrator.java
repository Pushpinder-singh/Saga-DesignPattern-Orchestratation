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
	
	@KafkaListener(topics = "order-events-topic", groupId = "saga-group")
    public void handleOrderEvent(String event) {
        System.out.println("Received Order Event: " + event);
		// Send Payment request after order creation
        paymentService.processPayment(event);
        // Publish to payment-events topic
    }

    @KafkaListener(topics = "payment-events-topic", groupId = "saga-group")
    public void handlePaymentEvent(String event) {
        System.out.println("Received Payment Event: " + event);
        // Trigger Inventory service after successful payment
        //if true
        inventoryService.reserveInventory(event);
        // else rollback if payment fails
    }

    @KafkaListener(topics = "inventory-events-topic", groupId = "saga-group")
    public void handleInventoryEvent(String event) {
        System.out.println("Received Inventory Event: " + event);
        
        // Finalize order if inventory is reserved
    }

}
