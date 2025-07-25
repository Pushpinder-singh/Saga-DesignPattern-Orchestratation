package com.saga.pattern.SagaOrchestration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SagaOrchestrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagaOrchestrationApplication.class, args);
		System.out.println("++++++ Saga Orchestration Application is up ++++");
		
	}

}
