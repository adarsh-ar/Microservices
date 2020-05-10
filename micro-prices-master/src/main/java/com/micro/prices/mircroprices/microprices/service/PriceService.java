package com.micro.prices.mircroprices.microprices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.prices.mircroprices.model.Price;
import com.micro.prices.mircroprices.repository.PriceRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;



@Service
public class PriceService {

	@Autowired
	private PriceRepository pricerRepository;
	
	@HystrixCommand(fallbackMethod = "getAllPricesFallBack")
	public List<Price> getAllPrices(){
		return pricerRepository.findAll();
	}
	
	private List<Price> getAllPricesFallBack() {
        return new ArrayList<>();
    }
	
	@HystrixCommand(fallbackMethod = "getPriceFallBack")
	public Price getPrice(Long productId) {
		return pricerRepository.findById(productId).orElse(null);
	}
	
	private Price getPriceFallBack(Long productId) {
        return null;
    }
	
	@HystrixCommand(fallbackMethod = "savePriceFallBack")
	public void savePrice(Price price){
		pricerRepository.save(price);
	}
	
	private void savePriceFallBack(Price price) {
       
    }
	
	@HystrixCommand(fallbackMethod = "updatePriceFallBack")
	public void updatePrice(Price price){
		 pricerRepository.save(price);
	}
	
	private void updatePriceFallBack(Price price) {

	}
	
	@HystrixCommand(fallbackMethod = "deletePriceFallBack")
	public void deletePrice(Long productId){
		 pricerRepository.deleteById(productId);
	}
	
	private void deletePriceFallBack(Long productId) {
		
    }
	
}
