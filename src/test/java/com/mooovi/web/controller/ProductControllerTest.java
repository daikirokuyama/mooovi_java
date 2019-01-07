package com.mooovi.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setupMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testShow() throws Exception {
		mockMvc.perform(get("/products/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("products/show")));
	}
	
	@Test
	public void testSearch() throws Exception {
		mockMvc.perform(get("/products/search").param("keyword", "the"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("products/search")))
			.andExpect(model().attribute("products",  hasSize(4)));
	}

}
