package com.micro.prices.mircroprices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.micro.prices.mircroprices.microprices.service.PriceService;
import com.micro.prices.mircroprices.model.Price;



@RestController
public class PriceController {
	
	@Autowired
	private PriceService priceService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/prices")
	public List<Price> getAllPrices(){
		
		return priceService.getAllPrices();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/price/{productId}")
	public Price getPriceByProductId(@PathVariable Long productId){
		
		return priceService.getPrice(productId);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/price/create")
	public void savePrice(@RequestBody Price price){
		
		priceService.savePrice(price);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/price")
	public void upatePrice(@RequestBody Price price){
		
		priceService.updatePrice(price);
		
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/price/{productId}")
	public void deletePrice(@PathVariable Long productId){
		
		priceService.deletePrice(productId);
		
	}
	
}
