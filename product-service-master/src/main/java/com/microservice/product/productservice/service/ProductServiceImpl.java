package com.microservice.product.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.product.productservice.entity.Product;
import com.microservice.product.productservice.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo repo;

	@Override
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	@Override
	public Product getProductById(long id) {
		return repo.findOne(id);
	}

	@Override
	public Product saveProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public void removeProduct(Product product) {
		repo.delete(product);

	}

}
