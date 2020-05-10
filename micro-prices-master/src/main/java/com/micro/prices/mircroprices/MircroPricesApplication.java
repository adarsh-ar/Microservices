package com.micro.prices.mircroprices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class MircroPricesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MircroPricesApplication.class, args);
	}

}
