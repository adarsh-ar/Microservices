package com.microservice.product.productservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.microservice.product.productservice.entity.Product;
import com.microservice.product.productservice.repository.ProductRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest {

	@Mock
	ProductRepo repo;

	@InjectMocks
	ProductServiceImpl impl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllProducts() {
		List<Product> product = new ArrayList<Product>();
		product.add(new Product(1007L, "123", "Apple-MAC", true, false, null));
		product.add(new Product(1008L, "124", "ASUS", true, false, null));
		product.add(new Product(1009L, "125", "DELL", true, false, null));
		when(repo.findAll()).thenReturn(product);
		List<Product> result = impl.getAllProducts();
		assertEquals(3, result.size());
	}

	@Test
	public void testGetProductById() { // fail("Not yet implemented");

		Product byIdProduct = new Product(Long.valueOf(1L), "123", "Apple-MAC", true, false, null);
		when(repo.findOne(1L)).thenReturn(byIdProduct);
		Product result = impl.getProductById(1L);
		assertEquals(Long.valueOf(1L), result.getProduct_Id());
		assertEquals("Apple-MAC", result.getDescription());
		assertEquals("123", result.getProduct_Name());

	}

	@Test
	public void testSaveProduct() { // fail("Not yet implemented");

		Product saveProduct = new Product(Long.valueOf(1L), "123", "Apple-MAC", true, false, null);
		when(repo.save(saveProduct)).thenReturn(saveProduct);
		Product result = impl.saveProduct(saveProduct);
		assertEquals(Long.valueOf(1L), result.getProduct_Id());
		assertEquals("Apple-MAC", result.getDescription());
		assertEquals("123", result.getProduct_Name());
	}

	@Test
	public void testRemoveProduct() { // fail("Not yet implemented");
		Product deleteProduct = new Product(Long.valueOf(1L), "123", "Apple-MAC", true, false, null);
		impl.removeProduct(deleteProduct);
		verify(repo, times(1)).delete(deleteProduct);
	}

}
