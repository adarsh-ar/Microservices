package com.microservice.product.productservice.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.product.productservice.entity.Product;
import com.microservice.product.productservice.entity.Response;
import com.microservice.product.productservice.repository.ProductRepo;
import com.microservice.product.productservice.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ProductController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductRepo repo;

	@Autowired
	ProductService service;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		logger.info("Fetching all the products from the database");

		return new ResponseEntity<List<Product>>(service.getAllProducts(), HttpStatus.OK);

	}

	@HystrixCommand(fallbackMethod = "fallbackFindById", groupKey = "Hello", threadPoolKey = "HelloHystrix")
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> findByPId(@PathVariable long id) {
		logger.info("searching for the mentioned Product Id ::" + id);

		Product product = service.getProductById(id);

		if (product == null && product.getProduct_Id() > 0) {
			throw new ProductException("No product available");
		}

		return new ResponseEntity<Product>(service.getProductById(id), HttpStatus.OK);

	}

	// FALLBACK for FIND BY IF
	public ResponseEntity<Product> fallbackFindById(long id) {
		Product product = new Product();
		product.setProduct_Id(id);
		product.setDescription("This product is not available Hence you are seeing this fallback");
		return new ResponseEntity<Product>(
				new Product(id, "HYSTRIX FALLBACK", "HYSTRIX FALLBACK", false, false, "HYSTRIX FALLBACK"),
				HttpStatus.NOT_FOUND);

	}

	@HystrixCommand(fallbackMethod = "getDeleteById", groupKey = "Hello", threadPoolKey = "HelloHystrix")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Response> deletedbyId(@PathVariable long id) {

		logger.info("Removing the mentioned Product Id ::" + id);

		Product product = service.getProductById(id);
		if (product == null || product.getProduct_Id() <= 0) {
			throw new ProductException("product to delete doesn´t exist");
		}

		service.removeProduct(product);

		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Product is delted"), HttpStatus.OK);
	}

	@RequestMapping(value = "/product/create", method = RequestMethod.POST)
	public ResponseEntity<Product> saveProduct(@RequestBody Product payload) throws ProductException {
		logger.info("Product to save " + payload);

		return new ResponseEntity<Product>(service.saveProduct(payload), HttpStatus.OK);
	}

	@RequestMapping(value = "/product", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@RequestBody Product payload) throws ProductException {
		logger.info("Payload to update " + payload);
		Product toDo = service.getProductById(payload.getProduct_Id());
		if (toDo == null || toDo.getProduct_Id() <= 0) {
			throw new ProductException("product to update doesn´t exist");
		}
		return new ResponseEntity<Product>(service.saveProduct(payload), HttpStatus.OK);
	}

	// FALLBACK FOR THE DELETE METHOD
	public ResponseEntity<Response> getDeleteById(long id) {
		Product product = new Product();
		logger.error("This product is not available Hence you are seeing this fallback");
		product.setProduct_Id(id);
		product.setDescription("This product is not available Hence you are seeing this fallback");
		return new ResponseEntity<Response>(
				new Response(404, "This product is not available Hence you are seeing this fallback"),
				HttpStatus.NOT_FOUND);
	}

}
