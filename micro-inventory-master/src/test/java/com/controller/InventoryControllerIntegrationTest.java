package com.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.entity.Inventory;
import com.service.InventoryService;
import com.util.JsonUtil;

@WebMvcTest//(InventoryController.class)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private InventoryService service;

	@Before
	public void setup() {
	}


	@Test
	public void testAddInventory() throws Exception {
		Inventory inv = Inventory.builder().productId(1l).availableQuantity(2l).minQuantity(1l).maxQuantity(2l)
				.totalQuantity(4l).returnQuantity(1l).manufacturer("Samsung").build();
		given(service.saveInventory(Mockito.any())).willReturn(inv);
		String json = JsonUtil.objectToString(inv);
		mvc.perform(post("/inventory").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isCreated()).andExpect(content().string(json));
		verify(service, VerificationModeFactory.times(1)).saveInventory(Mockito.any());
        reset(service);
	}
}
