package com.cognizant.drugservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.cognizant.drugservice.controller.DrugController;


@SpringBootTest
@AutoConfigureMockMvc
class DrugServiceApplicationTests {
	@Autowired
	private DrugController controller;
	
	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}
	@Test
	void test_get_searchDrugsByID() throws Exception {
		ResultActions actions = mvc.perform(get("/searchDrugsByID/1"));
		actions.andExpect(status().isOk());
	}
	@Test
	void test_get_searchDrugsByID_NotAvailable() throws Exception {
		ResultActions actions = mvc.perform(get("/searchDrugsByID/300"));
		actions.andExpect(status().is(500)); //internal server error as no data
	}
	@Test
	void test_get_searchDrugsByName() throws Exception {
		ResultActions actions = mvc.perform(get("/searchDrugsByName/Neurotin"));
		actions.andExpect(status().isOk());
	}
	@Test
	void test_get_searchDrugsByName_NotAvailable() throws Exception {
		ResultActions actions = mvc.perform(get("/searchDrugsByName/Mail-order-pharmacy"));
		actions.andExpect(status().is(500)); //internal server error as no data
	}
}
