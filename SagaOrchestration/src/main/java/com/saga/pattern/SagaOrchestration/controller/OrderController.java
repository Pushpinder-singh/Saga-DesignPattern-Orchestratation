package com.saga.pattern.SagaOrchestration.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.pattern.SagaOrchestration.dto.OrderDTO;
import com.saga.pattern.SagaOrchestration.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderDTO request) {
        orderService.placeOrder(request);
        return "Order request received!";
    }

}
