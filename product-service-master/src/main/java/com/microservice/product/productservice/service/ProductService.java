package com.microservice.product.productservice.service;

import java.util.List;

import com.microservice.product.productservice.entity.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProductById(long id);

	public Product saveProduct(Product product);

	void removeProduct(Product product);

}
