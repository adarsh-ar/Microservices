package com.micro.prices.mircroprices.controller;

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


import com.micro.prices.mircroprices.MircroPricesApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MircroPricesApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriceControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void testGetAllProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/prices").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}
	
	@Test
	public void testSaveProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/price/create").contentType(MediaType.APPLICATION_JSON)
				.content(" {\r\n" + "        \"productId\": 1004,\r\n" + "        \"price\": 20.89,\r\n"
						+ "        \"basePrice\": 20.89,\r\n"
						+ "        \"sellingPrice\": 20.89\r\n" + "     }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testUpdateProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/price").contentType(MediaType.APPLICATION_JSON)
				.content(" {\r\n" + "        \"productId\": 1004,\r\n" + "        \"price\": 20.89,\r\n"
						+ "        \"basePrice\": 20.89,\r\n"
						+ "        \"sellingPrice\": 20.89\r\n" + "     }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testDeletedbyId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/price/1001").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
	}

}
