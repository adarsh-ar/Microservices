package com.micro.productcompositeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.micro.productcompositeservice.model.ProductCreationModel;
import com.micro.productcompositeservice.model.Response;
import com.micro.productcompositeservice.service.ProductService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rx.Observable;



@RestController
@RequestMapping("/product")
public class ProductCompositeController {

	
	@Autowired
	private ProductService productService;
	
	@PostMapping( path = "/create")
	public ResponseEntity<String> createProducts(@RequestBody ProductCreationModel productModel){
		
		long millis = System.currentTimeMillis();
		
		productModel.setProductId(millis);
		productService.createProduct(productModel);
		
		
				
		return new ResponseEntity<String>("Product created sucessfully:"+millis, HttpStatus.OK);
	}
	
	@GetMapping( path = "/{productId}")
	@HystrixCommand(fallbackMethod = "getDeleteById")
	public DeferredResult<ProductCreationModel> getProducts(@PathVariable Long productId){
		
		DeferredResult<ProductCreationModel> deferred = new DeferredResult<>();
		Observable<ProductCreationModel> o = productService.getProductDetails(productId);
		o.subscribe(deferred::setResult, deferred::setErrorResult);
		    
		return deferred;

	}
	
	
	public ResponseEntity<Response> getDeleteById(long id) {
		return new ResponseEntity<Response>(
				new Response(404, "This product is not available Hence you are seeing this fallback"),
				HttpStatus.NOT_FOUND);
	}
	
}
