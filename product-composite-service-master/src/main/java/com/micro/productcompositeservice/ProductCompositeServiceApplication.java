package com.micro.productcompositeservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ProductCompositeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCompositeServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	@Qualifier("productCreateRestemplate")
	public RestTemplate productCreateRestemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@LoadBalanced
	@Qualifier("priceCreateRestemplate")
	public RestTemplate priceCreateRestemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@LoadBalanced
	@Qualifier("inventoryCreateRestemplate")
	public RestTemplate inventoryCreateRestemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@LoadBalanced
	@Qualifier("promotionCreateRestemplate")
	public RestTemplate promotionCreateRestemplate() {
		return new RestTemplate();
	}

}
