package com.aaponomarev.pricing;

import com.aaponomarev.pricing.domain.price.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@Autowired
	PriceRepository priceRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetById() throws Exception {
		mockMvc.perform(get("/prices/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.vehicleId").exists())
				.andExpect(jsonPath("$.vehicleId").value(1));
	}

	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get("/prices/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded").exists())
				.andExpect(jsonPath("$._embedded.prices").exists())
				.andExpect(jsonPath("$._embedded.prices", hasSize(2)));
	}

	@Test
	public void contextLoads() {
	}

}
