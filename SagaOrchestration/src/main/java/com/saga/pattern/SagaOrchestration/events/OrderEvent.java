package com.saga.pattern.SagaOrchestration.events;

public class OrderEvent {
    private String orderId;
    private String status; // PENDING, COMPLETED, FAILED
	
    public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}