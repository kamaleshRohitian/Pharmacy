package com.cts.subscription;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cts.subscription.controller.SubscriptionController;
import com.cts.subscription.entity.Prescription;



@SpringBootTest
@AutoConfigureMockMvc
class MainApplicationTests {
	@Autowired
	private SubscriptionController controller;
	
	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}
	@Test
	void test_get_subscriptionByMemberId() throws Exception {
		ResultActions actions = mvc.perform(get("/getAllSubscription/1"));
		actions.andExpect(status().isOk());
	}
	@Test
	void test_get_subscriptionByMemberId_NotAvailable() throws Exception {
		ResultActions actions = mvc.perform(get("/getAllSubscription/300"));
		actions.andExpect(status().is(500)); //internal server error as no data
	}
	
	@Test
	void test_subscribe() throws Exception {
		Prescription prescription=new Prescription((long) 10178,"2","Chennai","1234","LIC",LocalDate.now(),"Covaxin","Gabakotin",8,"weekly","john");
		ResultActions actions = mvc.perform(get("/subscribe"));
		actions.andExpect(status().isOk());
	}
	
}
