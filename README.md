# Saga-DesignPattern-Orchestratation
Orchestration-Based Saga (Centralized Control to build resilient and robust system)

This project implements the Saga Design Pattern in a Java microservices system, using Spring Boot and Kafka for orchestration. Saga orchestrator manages the entire workflow and publish the events for other service to carry out their operations.

**Tech Stack & Tools**
-Java 21
-Maven
-Apache Kafka
-Postman

**Use Case: E-commerce Order Service**
Order Service: Creates an order.
Payment Service: Processes payment.
Inventory Service: Reserves the items.
Rollback operation : If payment or inventory reservation fails, the order must be canceled.

**Directory Structure**
<img width="405" height="550" alt="image" src="https://github.com/user-attachments/assets/47489800-8747-4bae-846c-14720782b61d" />

Run the application and Hit the request using postman. Check logs in Kafka Consumer.
<img width="840" height="458" alt="image" src="https://github.com/user-attachments/assets/2f827039-d103-4909-aec7-ad7686bbb1a9" />
