package com.microservice.product.productservice.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.microservice.product.productservice.ProductServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProductServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void testGetAllProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}

	@Test
	public void testFindByproduct_Id() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product/1002").accept(MediaType.APPLICATION_JSON))

				.andExpect(status().is2xxSuccessful());


	}

	@Test
	public void testSaveProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/product/create").contentType(MediaType.APPLICATION_JSON)
				.content(" {\r\n" + "        \"product_Id\": 1002,\r\n" + "        \"product_Name\": \"MOBILE\",\r\n"
						+ "        \"description\": \"THis is an Exclusive\",\r\n"
						+ "        \"is_Sellable\": true,\r\n" + "        \"discount\": \"10%\",\r\n"
						+ "        \"returnable\": true\r\n" + "    }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.product_Id").exists()).andDo(print())
				.andExpect(jsonPath("$.product_Name").exists()).andDo(print())
				.andExpect(jsonPath("$.description").exists()).andDo(print())
				.andExpect(jsonPath("$.is_Sellable").exists()).andDo(print()).andExpect(jsonPath("$.discount").exists())
				.andDo(print()).andExpect(jsonPath("$.returnable").exists()).andDo(print())
				.andExpect(jsonPath("$.product_Id").value(1002)).andDo(print())
				.andExpect(jsonPath("$.product_Name").value("MOBILE")).andDo(print())
				.andExpect(jsonPath("$.description").value("THis is an Exclusive")).andDo(print())
				.andExpect(jsonPath("$.is_Sellable").value("true")).andDo(print())
				.andExpect(jsonPath("$.discount").value("10%")).andDo(print())
				.andExpect(jsonPath("$.returnable").value("true")).andDo(print());

	}

	@Test
	public void testUpdateProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/product").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "        \"product_Id\": 1004,\r\n" + "        \"product_Name\": \"REF\",\r\n"
						+ "        \"description\": \"THis is an Exclusive\",\r\n"
						+ "        \"is_Sellable\": true,\r\n" + "        \"discount\": \"10%\",\r\n"
						+ "        \"returnable\": true\r\n" + "    }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.product_Id").exists()).andDo(print())
				.andExpect(jsonPath("$.product_Name").exists()).andDo(print())
				.andExpect(jsonPath("$.description").exists()).andDo(print())
				.andExpect(jsonPath("$.is_Sellable").exists()).andDo(print()).andExpect(jsonPath("$.discount").exists())
				.andDo(print()).andExpect(jsonPath("$.returnable").exists()).andDo(print())
				.andExpect(jsonPath("$.product_Id").value(1004)).andDo(print())
				.andExpect(jsonPath("$.product_Name").value("REF")).andDo(print())
				.andExpect(jsonPath("$.description").value("THis is an Exclusive")).andDo(print())
				.andExpect(jsonPath("$.is_Sellable").value("true")).andDo(print())
				.andExpect(jsonPath("$.discount").value("10%")).andDo(print())
				.andExpect(jsonPath("$.returnable").value("true")).andDo(print());
	}

	@Test
	public void testDeletedbyId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1001").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status").value(200)).andExpect(jsonPath("$.message").value("Product is delted"))
				.andDo(print());

	}

}
