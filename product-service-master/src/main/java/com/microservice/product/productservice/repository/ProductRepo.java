package com.microservice.product.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.product.productservice.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
